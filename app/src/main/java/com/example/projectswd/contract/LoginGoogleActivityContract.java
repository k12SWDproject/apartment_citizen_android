package com.example.projectswd.contract;

import com.example.projectswd.model.LoginGoogle;
import com.example.projectswd.model.User;

public interface LoginGoogleActivityContract {
    interface presenter{

        void getUser(String token, String username);
        void loginGoole(LoginGoogle token);
    }
    interface view{
        void loginGooleSuccess(String token);
        void loginGooleFail(String msg);

        void getUserSuccess(User user);
        void getUserFail(String msg);
    }
}
