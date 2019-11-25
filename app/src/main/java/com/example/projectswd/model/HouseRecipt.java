package com.example.projectswd.model;

import java.io.Serializable;
import java.util.List;

public class HouseRecipt implements Serializable {

    private int houseId;
    private String houseName;
    private List<Receipts> listPayedReceipt;
    private List<Receipts> listNotPayedReceipt;

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

    public List<Receipts> getListPayedReceipt() {
        return listPayedReceipt;
    }

    public void setListPayedReceipt(List<Receipts> listPayedReceipt) {
        this.listPayedReceipt = listPayedReceipt;
    }

    public List<Receipts> getListNotPayedReceipt() {
        return listNotPayedReceipt;
    }

    public void setListNotPayedReceipt(List<Receipts> listNotPayedReceipt) {
        this.listNotPayedReceipt = listNotPayedReceipt;
    }
}
