package com.example.projectswd.utils;

public class ConfigAPI {
    public  static final String baseUrl = "https://spwproject.herokuapp.com/";

    public interface Api{
        String LOGIN = "login";
        String GETUSER ="api/users/{username}";
        String GETRECEIPTS ="api/receipts/user/{type}";
        String GETDETAILRECEIPT ="api/receipts";
        String PAYRECEIPT = "api/receipts/{id}/status";
    }
}
