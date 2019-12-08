package com.example.projectswd.presenters;

import com.example.projectswd.contract.ProfileActivityContract;
import com.example.projectswd.model.User;
import com.example.projectswd.model.UserUpdateDTO;
import com.example.projectswd.repositories.UserRepository;
import com.example.projectswd.repositories.UserRepositoryImp;
import com.example.projectswd.utils.CallBackData;

public class ProfileActivityPresenter implements ProfileActivityContract.presenter {
    private  ProfileActivityContract.view view;
    private UserRepository userRepository;

    public ProfileActivityPresenter(ProfileActivityContract.view view) {
        this.view = view;
        userRepository = new UserRepositoryImp();
    }

    @Override
    public void updateMember(String token, UserUpdateDTO username) {
        userRepository.updateMember(token, username, new CallBackData<User>() {
            @Override
            public void success(User user) {
                view.updateMemberSuccess(user);
            }

            @Override
            public void fail(String msg) {
                view.updateMemberFail(msg);
            }
        });
    }
}
