package com.example.projectswd.contract;

import com.example.projectswd.model.User;
import com.example.projectswd.model.UserUpdateDTO;

public interface MenuActivityContract {
    interface presenter{

        void getUser(String token, String username);
    }
    interface view{


        void getUserSuccess(User user);
        void getUserFail(String msg);
    }
}
