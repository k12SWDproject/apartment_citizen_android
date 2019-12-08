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

        TextView txtInfo = findViewById(R.id.txtInfo);

        Intent intent = getIntent();
         user = (User) intent.getSerializableExtra("USERINFO");
         token = intent.getStringExtra("TOKEN");
         house = user.getHouse();


        txtInfo.setText(user.getId() +" - "+house.getHouseName());

         TextView txtName = findViewById(R.id.txtNameOwner);
         txtName.setText(user.getUsername());
         TextView txtApID = findViewById(R.id.txtAparmentID);
         txtApID.setText(house.getHouseName());
         txtInfo.setText(user.getId() +" - "+house.getHouseName());
    }

    public void clickToElectric(View view) {
        Intent  intent = new Intent(getApplicationContext(), ElectricActivity.class);
        intent.putExtra("TOKEN", token);
        intent.putExtra("USERINFO",user);
        startActivity(intent);
    }


    public void clickToWater(View view) {
        Intent  intent = new Intent(getApplicationContext(), WaterActivity.class);
        intent.putExtra("TOKEN", token);
        intent.putExtra("USERINFO",user);
        startActivity(intent);
    }

//    public void clickToProfile(View view) {
//        Intent  intent = new Intent(getApplicationContext(), ProfileActivity.class);
//        intent.putExtra("TOKEN", token);
//        intent.putExtra("USERINFO",user);
//        startActivity(intent);
//    }
    public void clickToMemberManage() {
        Intent  intent = new Intent(getApplicationContext(), ManageMembersActivity.class);
        intent.putExtra("TOKEN", token);
        intent.putExtra("USERINFO",user);
        startActivity(intent);
    }
}
