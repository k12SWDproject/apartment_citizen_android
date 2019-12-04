package com.example.projectswd.views;

import com.example.projectswd.model.HouseRecipt;
import com.example.projectswd.model.User;

import java.util.List;

public interface GetListMemberView {
    void getListMemberSuccess(List<User> users);
    void getListMemberFail(String msg);
}
