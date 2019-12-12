package com.example.projectswd.repositories;

import com.example.projectswd.model.FilterEmail;
import com.example.projectswd.model.FilterHouse;
import com.example.projectswd.model.User;
import com.example.projectswd.model.UserUpdateDTO;
import com.example.projectswd.utils.CallBackData;

import java.util.List;

import okhttp3.ResponseBody;

public interface UserRepository {
    void getListMember(String token, FilterHouse filterHouse, CallBackData<List<User>> callBackData );
    void getMemberByEmail(String token, FilterEmail filterEmail, CallBackData<List<User>> callBackData);
    void addMember (String token, String username, CallBackData<ResponseBody> callBackData);
    void deleteMember(String token, String username, CallBackData<ResponseBody> callBackData);
    void updateMember(String token, UserUpdateDTO user, CallBackData<User> callBackData);
    void getUserByUsername(String token, String username, CallBackData<User> callBackData);
}
