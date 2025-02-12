package eu.mithril.invoiceservice.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import eu.mithril.invoiceservice.model.Invoice;
import eu.mithril.invoiceservice.model.User;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class InvoiceService {

    private final UserService userService;
    private final String pdfUrl;
    private final JdbcTemplate jdbcTemplate;

    List<Invoice> invoices = new CopyOnWriteArrayList<>();

    public InvoiceService(
            UserService userService,
            @Value("${pdf.url}") String pdfUrl, JdbcTemplate jdbcTemplate
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


    public List<Invoice> findAll() {
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
        User user = userService.findById(userId);
        Optional.ofNullable(user).orElseThrow(IllegalStateException::new);

        var invoice = new Invoice(userId, pdfUrl, amount);
        invoices.add(invoice);
        return invoice;
    }

}
