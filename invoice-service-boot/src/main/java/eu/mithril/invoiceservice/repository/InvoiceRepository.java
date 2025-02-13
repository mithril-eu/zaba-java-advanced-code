package eu.mithril.invoiceservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import eu.mithril.invoiceservice.model.Invoice;

public interface InvoiceRepository extends CrudRepository<Invoice, String> {

    Iterable<Invoice> findByUserId(@Param("userId") String userId);
    Iterable<Invoice> findByUserIdAndAmountIsLessThan(@Param("amount") Integer amount);

}
