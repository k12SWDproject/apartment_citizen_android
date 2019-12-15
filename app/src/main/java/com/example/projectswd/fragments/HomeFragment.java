package com.example.projectswd.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.projectswd.R;
import com.example.projectswd.contract.HomeFragmentContract;
import com.example.projectswd.model.House;
import com.example.projectswd.model.User;
import com.example.projectswd.presenters.HomeFragmentPresenter;

import java.text.DecimalFormat;
import java.text.NumberFormat;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HomeFragmentContract.view {
    private User user;
//    private House house;
    private String token, username;
    TextView txtMoney;
    private HomeFragmentPresenter presenter;
    public HomeFragment( ) {
        // Required empty public constructor

    }


    @Override
    public void onStart() {
        super.onStart();
        username = getArguments().getString("USERNAME");
        token = getArguments().getString("TOKEN");
//        house = user.getHouse();
        initPresenter();
        presenter.getUser(token,username);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        txtMoney = view.findViewById(R.id.txtMoneyOfUser);

        return  view;
    }

    private void initPresenter(){
        presenter = new HomeFragmentPresenter(this);
    }

    @Override
    public void getUserSuccess(User user) {
        if(user.getMoney()==null){

            txtMoney.setText("0 VNĐ");
        }else{
            NumberFormat formatter = new DecimalFormat("#,###");
            String formattedNumber = formatter.format(user.getMoney());
            txtMoney.setText(formattedNumber+" VNĐ");
        }
    }

    @Override
    public void getUserFail(String msg) {

    }
}
