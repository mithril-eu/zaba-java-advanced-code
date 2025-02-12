package eu.mithril.invoiceservice.web;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eu.mithril.invoiceservice.model.Invoice;
import eu.mithril.invoiceservice.service.InvoiceService;
import eu.mithril.invoiceservice.web.dto.InvoiceDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Validated
@RestController
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/invoices")
    public List<Invoice> invoices() {
        return invoiceService.findAll();
    }

    @PostMapping("/invoices")
    public Invoice createInvoice(
            @RequestParam("user_id") @NotBlank String userId,
            @RequestParam("amount") @Min(10) @Max(100) Integer amount
    ) {
        return invoiceService.create(userId, amount);
    }

    @PostMapping("/invoices/{userId}/{amount}")
    public Invoice createInvoicePath(
            @PathVariable("userId") String userId,
            @PathVariable("amount") Integer amount
    ) {
        return invoiceService.create(userId, amount);
    }

    @PostMapping("/invoices/dto")
    public Invoice createInvoicePath(@RequestBody @Valid InvoiceDto invoiceDto) {
        return invoiceService.create(invoiceDto.getUserId(), invoiceDto.getAmount());
    }

}
