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
    User user;
    House house;
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent = getIntent();
         user = (User) intent.getSerializableExtra("USERINFO");
         token = intent.getStringExtra("TOKEN");
         house = user.getHouse();
         TextView txtName = findViewById(R.id.txtNameOwner);
         txtName.setText(user.getUsername());
         TextView txtApID = findViewById(R.id.txtAparmentID);
         txtApID.setText(house.getHouseName());
         TextView txtInfo = findViewById(R.id.txtInfo);
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

    public void clickToProfile(View view) {
        Intent  intent = new Intent(getApplicationContext(), ProfileActivity.class);
        intent.putExtra("TOKEN", token);
        intent.putExtra("USERINFO",user);
        startActivity(intent);
    }
}
