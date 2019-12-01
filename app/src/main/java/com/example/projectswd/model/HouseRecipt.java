package com.example.projectswd.model;

import java.io.Serializable;
import java.util.List;

public class HouseRecipt implements Serializable {

    private int houseId;
    private String houseName;
    private List<ReceiptItem> listPayedReceipt;
    private List<ReceiptItem> listNotPayedReceipt;

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public List<ReceiptItem> getListPayedReceipt() {
        return listPayedReceipt;
    }

    public void setListPayedReceipt(List<ReceiptItem> listPayedReceipt) {
        this.listPayedReceipt = listPayedReceipt;
    }

    public List<ReceiptItem> getListNotPayedReceipt() {
        return listNotPayedReceipt;
    }

    public void setListNotPayedReceipt(List<ReceiptItem> listNotPayedReceipt) {
        this.listNotPayedReceipt = listNotPayedReceipt;
    }
}
