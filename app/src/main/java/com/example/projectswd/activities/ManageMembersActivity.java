package com.example.projectswd.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.projectswd.R;
import com.example.projectswd.adapter.MemberAdapter;
import com.example.projectswd.model.House;
import com.example.projectswd.model.User;

import java.util.ArrayList;
import java.util.List;

public class ManageMembersActivity extends AppCompatActivity {
    ListView listView;
    House house;
    MemberAdapter memberAdapter;
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_members);
        final Intent intent = getIntent();
//        User user = (User) intent.getSerializableExtra("USERINFO");
        token = intent.getStringExtra("TOKEN");
//        house = user.getHouse();
        listView = findViewById(R.id.listManageMembers);


        List<User> listUser = new ArrayList<>();

        listUser.add(new User("AB123","Nguyen Minh Tri"));
        listUser.add(new User("AB123","Nguyen Minh Tri"));
        memberAdapter = new MemberAdapter();
        memberAdapter.setListUser(listUser);
        listView.setAdapter(memberAdapter);
    }
}
