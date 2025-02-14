package eu.mithril.invoiceservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import eu.mithril.invoiceservice.model.Invoice;
import eu.mithril.invoiceservice.model.User;
import eu.mithril.invoiceservice.repository.InvoiceRepository;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InvoiceServiceTest {

    private final UserService userService = Mockito.mock(UserService.class);
    String pdfUrl = "some-url";
    private final InvoiceRepository invoiceRepository = Mockito.mock(InvoiceRepository.class);

    private final InvoiceService underTest = new InvoiceService(userService, pdfUrl, invoiceRepository);

    private static final User USER = new User("user-id", "user-name");

    @Test
    void createInvoice() {
        // given
        var testInvoice = new Invoice("user-id", pdfUrl, 100);
        when(userService.findById(any(String.class))).thenReturn(USER);
        when(invoiceRepository.save(any(Invoice.class))).thenReturn(testInvoice);

        // when
        var resultInvoice = underTest.create("user-id", 100);

        // then
        assertNotNull(resultInvoice);
        assertEquals(100, resultInvoice.getAmount());
        assertEquals(pdfUrl, resultInvoice.getPdfUrl());
        assertEquals("user-id", resultInvoice.getUserId());

        verify(userService).findById("user-id");
        verify(invoiceRepository).save(any(Invoice.class));
    }

    @Test
    void createInvoice_() {
        // given
        ArgumentCaptor<Invoice> invoiceCaptor = ArgumentCaptor.forClass(Invoice.class);

        when(userService.findById(any(String.class))).thenReturn(USER);
        when(invoiceRepository.save(any(Invoice.class)))
                .thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        // when
        var resultInvoice = underTest.create("user-id", 100);

        // then
        verify(invoiceRepository).save(invoiceCaptor.capture());
        Invoice capturedInvoice = invoiceCaptor.getValue();

        assertAll(
                () -> assertEquals("user-id", capturedInvoice.getUserId(), "User id should match"),
                () -> assertEquals(100, capturedInvoice.getAmount(), "Amount should be 100"),
                () -> assertNull(capturedInvoice.getId(), "Id should be null before save")
        );

    }


}