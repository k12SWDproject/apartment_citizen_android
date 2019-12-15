package com.example.projectswd.contract;

import com.example.projectswd.model.User;

public interface HomeFragmentContract {
    interface presenter{
        void getUser(String token, String username);
    }
    interface view{
        void getUserSuccess(User user);
        void getUserFail(String msg);
    }
}
