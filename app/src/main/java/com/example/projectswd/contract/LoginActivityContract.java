package com.example.projectswd.contract;

import com.example.projectswd.model.User;

public interface LoginActivityContract {
    interface presenter{
        void login(String username, String password);
        void getUser(String token, String username);
    }
    interface  view {
        void loginSuccess(String token);
        void loginFail(String msg);

        void getUserSuccess(User user);
        void getUserFail(String msg);
    }
}
