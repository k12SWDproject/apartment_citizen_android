package com.example.projectswd.model;

import java.io.Serializable;
import java.math.BigInteger;

public class CartObject implements Serializable {

    private int id;
    private int quantity;
    private String name;
    private String image;
    private BigInteger price;
    private int quantityOfCart;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BigInteger getPrice() {
        return price;
    }

    public void setPrice(BigInteger price) {
        this.price = price;
    }

    public int getQuantityOfCart() {
        return quantityOfCart;
    }

    public void setQuantityOfCart(int quantityOfCart) {
        this.quantityOfCart = quantityOfCart;
    }
}
