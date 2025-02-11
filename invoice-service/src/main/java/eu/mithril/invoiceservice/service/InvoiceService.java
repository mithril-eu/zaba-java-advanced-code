package eu.mithril.invoiceservice.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import eu.mithril.invoiceservice.model.Invoice;
import eu.mithril.invoiceservice.model.User;

public class InvoiceService {

    private final UserService userService;

    List<Invoice> invoices = new CopyOnWriteArrayList<>();

    public InvoiceService(UserService userService) {
        this.userService = userService;
    }

    public List<Invoice> findAll() {
        return invoices;
    }

    public Invoice create(String userId, Integer amount) {
        User user = userService.findById(userId);
        Optional.ofNullable(user).orElseThrow(IllegalStateException::new);

        var invoice = new Invoice(
                userId,
                "https://pdfobject.com/pdf/sample.pdf"
                , amount
        );
        invoices.add(invoice);
        return invoice;
    }
}
