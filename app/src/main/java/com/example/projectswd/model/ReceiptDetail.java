package com.example.projectswd.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ReceiptDetail implements Serializable {
    private int Id;
    private BigDecimal total;
    private int unitPrice;
    private int quantity;
    private int utilityServiceId;
    private Date createDate;
    private Date lastModified;
    private  int receiptId;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUtilityServiceId() {
        return utilityServiceId;
    }

    public void setUtilityServiceId(int utilityServiceId) {
        this.utilityServiceId = utilityServiceId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public int getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }
}
