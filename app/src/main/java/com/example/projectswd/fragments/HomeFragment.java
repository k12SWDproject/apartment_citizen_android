package com.example.projectswd.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projectswd.R;
import com.example.projectswd.activities.ElectricActivity;
import com.example.projectswd.activities.OrtherActivity;

import com.example.projectswd.activities.WaterActivity;
import com.example.projectswd.activities.WifiActivity;
import com.example.projectswd.model.House;
import com.example.projectswd.model.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private User user;
    private House house;
    private String token;
    TextView txtMoney;

    public HomeFragment( ) {


    }

    @Override
    public void onStart() {
        super.onStart();
        user = (User) getArguments().getSerializable("USERINFO");
        token = getArguments().getString("TOKEN");
        house = user.getHouse();


        if(user.getMoney()==null){
            txtMoney.setText("0 VND");
        }else{
            txtMoney.setText(user.getMoney()+" - VND");
        }





    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        txtMoney = view.findViewById(R.id.txtMoneyOfUser);

        return  view;
    }


}
