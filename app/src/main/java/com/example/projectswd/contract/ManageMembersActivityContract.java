package com.example.projectswd.contract;

import com.example.projectswd.model.FilterEmail;
import com.example.projectswd.model.FilterHouse;
import com.example.projectswd.model.FilterName;
import com.example.projectswd.model.User;

import java.util.List;

import okhttp3.ResponseBody;

public interface ManageMembersActivityContract {
    interface  presenter{
        void getListMember(String token, FilterHouse filterHouse);
        void getMemberByEmail(String token, FilterEmail filterEmail);
        void addMember(String token, String username);
        void deleteMember(String token, String username);

    }
    interface view{
        void getListMemberSuccess(List<User> users);
        void getListMemberFail(String msg);
        void getMemberByEmailSuccess(List<User> users);
        void getMemberByEmailFail(String msg);
        void addMemberSuccess(ResponseBody responseBody);
        void addMemberFail(String msg);
        void deleteMemberSuccess(ResponseBody responseBody);
        void deleteMemberFail(String msg);
    }
}
