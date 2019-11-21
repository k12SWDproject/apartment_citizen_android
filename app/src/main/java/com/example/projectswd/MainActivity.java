package com.example.projectswd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.projectswd.model.House;
import com.example.projectswd.model.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        User user = new User();
        House house = new House();

        Intent intent = getIntent();
         user = (User) intent.getSerializableExtra("USERINFO");
         house = user.getHouse();
        TextView txtInfo = findViewById(R.id.txtInfo);
        txtInfo.setText(user.getId() +" - "+house.getHouseName());
    }
}
