package com.example.projectswd.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.projectswd.R;
import com.example.projectswd.model.House;
import com.example.projectswd.model.User;

public class MainActivity extends AppCompatActivity {
    private User user;
     private House house;
     private String token;
    TextView txtMoney;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    public void clickToWifi(View view) {
        Intent  intent = new Intent(getApplicationContext(), WifiActivity.class);
        intent.putExtra("TOKEN", token);
        intent.putExtra("USERINFO",user);
        startActivity(intent);
    }

    public void clickToPhiChungCu(View view) {
        Intent  intent = new Intent(getApplicationContext(), OrtherActivity.class);
        intent.putExtra("TOKEN", token);
        intent.putExtra("USERINFO",user);
        startActivity(intent);
    }
}
