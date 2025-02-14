package eu.mithril.invoiceservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import eu.mithril.invoiceservice.model.Invoice;
import eu.mithril.invoiceservice.model.User;
import eu.mithril.invoiceservice.repository.InvoiceRepository;

@Component
public class InvoiceService {

    private final UserService userService;
    private final String pdfUrl;
    private final InvoiceRepository invoiceRepository;

    public InvoiceService(
            UserService userService,
            @Value("${pdf.url}") String pdfUrl,
            InvoiceRepository invoiceRepository
    ) {
        this.userService = userService;
        this.pdfUrl = pdfUrl;
        this.invoiceRepository = invoiceRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public List<Invoice> findAll() {
        System.out.println("database transaction open=" +
                           TransactionSynchronizationManager.isActualTransactionActive());
        return StreamSupport.stream(invoiceRepository.findAll().spliterator(), false).toList();
    }

    public List<Invoice> findByUserId(String userId) {
        return StreamSupport.stream(invoiceRepository.findByUserId(userId).spliterator(), false).toList();
    }

    public Invoice create(String userId, Integer amount) {
        System.out.println("database transaction open=" +
                           TransactionSynchronizationManager.isActualTransactionActive());
        User user = userService.findById(userId);
        Optional.ofNullable(user).orElseThrow(IllegalStateException::new);
        Invoice invoice = new Invoice(userId, pdfUrl, amount);
        return invoiceRepository.save(invoice);
    }

}
