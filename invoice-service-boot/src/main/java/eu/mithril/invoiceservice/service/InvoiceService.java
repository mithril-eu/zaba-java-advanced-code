package eu.mithril.invoiceservice.service;

import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import eu.mithril.invoiceservice.model.Invoice;
import eu.mithril.invoiceservice.model.User;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class InvoiceService {

    private final UserService userService;
    private final String pdfUrl;
    private final JdbcTemplate jdbcTemplate;

    public InvoiceService(
            UserService userService,
            @Value("${pdf.url}") String pdfUrl,
            JdbcTemplate jdbcTemplate
    ) {
        this.userService = userService;
        this.pdfUrl = pdfUrl;
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void mama() {
        System.out.println("Fetching pdf from external service");
    }

    @PreDestroy
    public void shutdown() {
        System.out.println("cleaning open resources");
    }


    @Transactional(isolation = Isolation.SERIALIZABLE)
    public List<Invoice> findAll() {
        System.out.println("database transaction open=" +
                           TransactionSynchronizationManager.isActualTransactionActive());
        String query = "select id, user_id, pdf_url, amount from invoices";

        return jdbcTemplate.query(query, (rs, rowNum) -> {
            var invoice = new Invoice();
            invoice.setId(rs.getObject("id").toString());
            invoice.setPdfUrl(rs.getString("pdf_url"));
            invoice.setUserId(rs.getString("user_id"));
            invoice.setAmount(rs.getInt("amount"));
            return invoice;
        });
    }

    public Invoice create(String userId, Integer amount) {
        System.out.println("database transaction open=" +
                           TransactionSynchronizationManager.isActualTransactionActive());
        User user = userService.findById(userId);
        Optional.ofNullable(user).orElseThrow(IllegalStateException::new);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        String insert = """
                insert into invoices (user_id, pdf_url, amount)
                values (?, ?, ?)
                """;

        jdbcTemplate.update(connection -> {
            var ps = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, userId);
            ps.setString(2, pdfUrl);
            ps.setInt(3, amount);
            return ps;
        }, keyHolder);

        String uuid = !keyHolder.getKeys().isEmpty() ?
                keyHolder.getKeys().values().iterator().next().toString()
                : null;

        return new Invoice(uuid, userId, pdfUrl, amount);
    }

}
