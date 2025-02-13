package eu.mithril.invoiceservice.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
@Profile("dev")
public class DevInvoiceServiceLoader {

    private final InvoiceService invoiceService;

    public DevInvoiceServiceLoader(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostConstruct
    public void setup() {
        System.out.println("inserting dev invoices...");
        invoiceService.create("someUser", 100);
        invoiceService.create("someOtherUser", 200);
    }
}
