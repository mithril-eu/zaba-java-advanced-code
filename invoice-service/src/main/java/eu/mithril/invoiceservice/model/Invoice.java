package eu.mithril.invoiceservice.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Invoice {
    private String id;
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("pdf_url")
    private String pdfUrl;
    private Integer amount;

    public Invoice() {
    }
    public Invoice(String userId, String pdfUrl, Integer amount) {
        this.id = UUID.randomUUID().toString();
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
