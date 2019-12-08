package com.example.projectswd.presenters;

import com.example.projectswd.contract.ManageMembersActivityContract;
import com.example.projectswd.model.FilterEmail;
import com.example.projectswd.model.FilterHouse;
import com.example.projectswd.model.FilterName;
import com.example.projectswd.model.User;
import com.example.projectswd.repositories.UserRepository;
import com.example.projectswd.repositories.UserRepositoryImp;
import com.example.projectswd.utils.CallBackData;

import java.util.List;

import okhttp3.ResponseBody;

public class ManageMembersActivityPresenter implements ManageMembersActivityContract.presenter {


    private ManageMembersActivityContract.view view;
    private UserRepository userRepository;

    public ManageMembersActivityPresenter(ManageMembersActivityContract.view view) {
        this.view = view;
        userRepository = new UserRepositoryImp();
    }

    @Override
    public void getListMember(String token, FilterHouse filterHouse){
        userRepository.getListMember(token, filterHouse, new CallBackData<List<User>>() {
            @Override
            public void success(List<User> users) {
                view.getListMemberSuccess(users);
            }

            @Override
            public void fail(String msg) {
                view.getListMemberFail(msg);
            }
        });

    }

    @Override
    public void getMemberByEmail(String token, FilterEmail filterEmail) {
        userRepository.getMemberByEmail(token, filterEmail, new CallBackData<List<User>>() {
            @Override
            public void success(List<User> users) {
                view.getMemberByEmailSuccess(users);
            }

            @Override
            public void fail(String msg) {
                view.getMemberByEmailFail(msg);
            }
        });
    }

    @Override
    public void addMember(String token, String username) {
        userRepository.addMember(token, username, new CallBackData<ResponseBody>() {
            @Override
            public void success(ResponseBody responseBody) {
                view.addMemberSuccess(responseBody);
            }

            @Override
            public void fail(String msg) {
                view.addMemberFail(msg);
            }
        });
    }

    @Override
    public void deleteMember(String token, String username) {
        userRepository.deleteMember(token, username, new CallBackData<ResponseBody>() {
            @Override
            public void success(ResponseBody responseBody) {
                view.deleteMemberSuccess(responseBody);
            }

            @Override
            public void fail(String msg) {
                view.deleteMemberFail(msg);
            }
        });
    }
}
