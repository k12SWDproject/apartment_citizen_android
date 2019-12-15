package com.example.projectswd.presenters;

import com.example.projectswd.contract.LoginGoogleActivityContract;

import com.example.projectswd.model.LoginGoogle;
import com.example.projectswd.model.User;
import com.example.projectswd.repositories.UserRepository;
import com.example.projectswd.repositories.UserRepositoryImp;
import com.example.projectswd.utils.CallBackData;

public class LoginGoogleActivityPresenter implements LoginGoogleActivityContract.presenter {
    private LoginGoogleActivityContract.view view;
    private UserRepository userRepository;

    public LoginGoogleActivityPresenter(LoginGoogleActivityContract.view view) {
        this.view = view;
        userRepository = new UserRepositoryImp();
    }


    @Override
    public void getUser(String token, String username) {
        userRepository.getUserByUsername(token, username, new CallBackData<User>() {
            @Override
            public void success(User user) {
                view.getUserSuccess(user);
            }

            @Override
            public void fail(String msg) {
                view.getUserFail(msg);
            }


        });
    }

    @Override
    public void loginGoole(LoginGoogle token) {
        userRepository.loginGoogle(token, new CallBackData<String>() {
            @Override
            public void success(String s) {
                view.loginGooleSuccess(s);
            }

            @Override
            public void fail(String msg) {
                view.loginGooleFail(msg);
            }


        });
    }
}