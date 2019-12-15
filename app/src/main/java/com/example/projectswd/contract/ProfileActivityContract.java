package com.example.projectswd.contract;

import com.example.projectswd.model.User;
import com.example.projectswd.model.UserUpdateDTO;

import okhttp3.ResponseBody;

public interface ProfileActivityContract {
    interface presenter{
        void updateMember(String token, UserUpdateDTO username);
        void getUser(String token, String username);
    }
    interface view{
        void updateMemberSuccess(User user);
        void updateMemberFail(String msg);

        void getUserSuccess(User user);
        void getUserFail(String msg);
    }
}
