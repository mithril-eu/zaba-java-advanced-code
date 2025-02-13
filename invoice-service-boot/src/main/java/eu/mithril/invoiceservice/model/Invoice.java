package eu.mithril.invoiceservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Table("INVOICES")
public class Invoice {

    @Id
    private String id;
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("pdf_url")
    private String pdfUrl;
    private Integer amount;

    public Invoice() {
    }
    public Invoice(String userId, String pdfUrl, Integer amount) {
        this.userId = userId;
        this.pdfUrl = pdfUrl;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
