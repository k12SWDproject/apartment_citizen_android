package com.example.projectswd.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectswd.R;
import com.example.projectswd.adapter.ElectricAdapter;
import com.example.projectswd.model.HouseRecipt;
import com.example.projectswd.model.User;
import com.example.projectswd.presenters.GetListReciptPresenter;
import com.example.projectswd.views.GetListReciptView;

public class WaterActivity extends AppCompatActivity implements GetListReciptView {

    ListView lvListNotPayedReceipt, lvListPayedReceipt;
    TextView txtUserinfo;
    String token;
    User user;
    private HouseRecipt houseRecipts;
    private ElectricAdapter adapter;
    private ElectricAdapter adapter2;
    private GetListReciptPresenter getListReciptPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);
        Intent intent = getIntent();
        token = intent.getStringExtra("TOKEN");
        user = (User) intent.getSerializableExtra("USERINFO");

        getListReciptPresenter = new GetListReciptPresenter(this);
        getListReciptPresenter.getList(token, "WATER_TYPE");


        lvListPayedReceipt = findViewById(R.id.lvNuocDaThanhToan);
        lvListNotPayedReceipt = findViewById(R.id.lvNuocChuaThanhToan);
        txtUserinfo = findViewById(R.id.txtUserInfo);

        txtUserinfo.setText(user.getHouse().getHouseName());
        lvListPayedReceipt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        lvListNotPayedReceipt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }

    @Override
    public void getListReciptSuccess(HouseRecipt houseRecipt) {
        houseRecipts = houseRecipt;
        adapter = new ElectricAdapter();
        adapter2 = new ElectricAdapter();
        adapter.setListReceipt(houseRecipts.getListPayedReceipt());
        lvListPayedReceipt.setAdapter(adapter);
        adapter2.setListReceipt(houseRecipts.getListNotPayedReceipt());
        lvListNotPayedReceipt.setAdapter(adapter2);
    }

    @Override
    public void getListReciptFail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
