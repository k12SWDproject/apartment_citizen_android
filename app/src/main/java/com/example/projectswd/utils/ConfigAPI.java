package com.example.projectswd.utils;

public class ConfigAPI {
    public  static final String baseUrl = "https://spwproject.herokuapp.com/";

    public interface Api{
        String LOGIN = "login";
        String LOGINGOOGLE = "api/login/google";
        String GETUSER ="api/users/{username}";
        String GETRECEIPTS ="api/receipts/user/{type}";
        String GETDETAILRECEIPT ="api/receipts";
        String PAYRECEIPT = "api/receipts/{id}/status";
        String GETPRODUCTS = "api/products";
        String GETMEMBERS ="api/users";
        String PAYMENT = "api/orders";
        String GETORDERS = "api/orders/my-order";
        String UPDATELISTMEMER = "api/houses/member/{username}";

    }
}
