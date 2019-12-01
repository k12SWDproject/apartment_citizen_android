package com.example.projectswd.model;

import java.io.Serializable;
import java.util.List;

public class ReceiptDTO implements Serializable {

    private Receipt receipt;
    private List<ReceiptDetail> receiptDetailList;

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }


    public List<ReceiptDetail> getReceiptDetailList() {
        return receiptDetailList;
    }

    public void setReceiptDetailList(List<ReceiptDetail> receiptDetailList) {
        this.receiptDetailList = receiptDetailList;
    }
}
