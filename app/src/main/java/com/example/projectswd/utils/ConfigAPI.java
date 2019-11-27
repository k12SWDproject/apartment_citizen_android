package com.example.projectswd.utils;

public class ConfigAPI {
    public  static final String baseUrl = "https://spwproject.herokuapp.com/";

    public interface Api{
        String LOGIN = "login";
        String GETUSER ="api/user/{username}";
        String GETRECEIPTS ="api/receipts/user/{type}";
    }
}
