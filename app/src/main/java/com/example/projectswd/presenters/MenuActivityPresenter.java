package com.example.projectswd.presenters;

import com.example.projectswd.contract.MenuActivityContract;
import com.example.projectswd.contract.ProfileActivityContract;
import com.example.projectswd.model.User;
import com.example.projectswd.model.UserUpdateDTO;
import com.example.projectswd.repositories.UserRepository;
import com.example.projectswd.repositories.UserRepositoryImp;
import com.example.projectswd.utils.CallBackData;

public class MenuActivityPresenter implements MenuActivityContract.presenter {
    private  MenuActivityContract.view view;
    private UserRepository userRepository;

    public MenuActivityPresenter(MenuActivityContract.view view) {
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
}
