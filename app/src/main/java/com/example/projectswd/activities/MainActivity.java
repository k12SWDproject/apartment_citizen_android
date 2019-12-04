package com.example.projectswd.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.projectswd.R;
import com.example.projectswd.contract.MainActivityContract;
import com.example.projectswd.model.House;
import com.example.projectswd.model.User;
//import com.example.projectswd.presenters.DetailReceiptPresenter;

public class MainActivity extends AppCompatActivity  {
    private User user;
     private House house;
     private String token;
    TextView txtMoney;

//    private DetailReceiptPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initPresenter();

        txtMoney = findViewById(R.id.txtMoneyOfUser);
        TextView txtInfo = findViewById(R.id.txtInfo);

        Intent intent = getIntent();
         user = (User) intent.getSerializableExtra("USERINFO");
         token = intent.getStringExtra("TOKEN");
         house = user.getHouse();


         if(user.getMoney()==null){
             txtMoney.setText("0 VND");
         }else{
             txtMoney.setText(user.getMoney()+"VND");
         }

        txtInfo.setText(user.getId() +" - "+house.getHouseName());

         TextView txtName = findViewById(R.id.txtNameOwner);
         txtName.setText(user.getUsername());
         TextView txtApID = findViewById(R.id.txtAparmentID);
         txtApID.setText(house.getHouseName());
         txtInfo.setText(user.getId() +" - "+house.getHouseName());

//         presenter.login();
    }

//    private void initPresenter(){
//        presenter = new DetailReceiptPresenter(this);
//    }


//    @Override
//    public void success(String abc) {
//
//    }
//
//    @Override
//    public void failure(String message) {
//
//    }
}
