package com.example.projectswd.presenters;

import com.example.projectswd.contract.LoginActivityContract;
import com.example.projectswd.model.User;
import com.example.projectswd.repositories.UserRepository;
import com.example.projectswd.repositories.UserRepositoryImp;
import com.example.projectswd.utils.CallBackData;

public class LoginActivityPresenter implements LoginActivityContract.presenter {

    private LoginActivityContract.view view;
    private UserRepository userRepository;

    public LoginActivityPresenter(LoginActivityContract.view view) {
        this.view = view;
        userRepository = new UserRepositoryImp();
    }

    @Override
    public void login(String username, String password) {
        userRepository.login(username, password, new CallBackData<String>() {
            @Override
            public void success(String s) {
                view.loginSuccess(s);
            }

            @Override
            public void fail(String msg) {
                view.loginFail(msg);
            }
        });
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
}
