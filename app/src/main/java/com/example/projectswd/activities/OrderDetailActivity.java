package com.example.projectswd.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.projectswd.R;
import com.example.projectswd.adapter.OrderDetailAdapter;
import com.example.projectswd.model.OrderDetail;
import com.example.projectswd.model.User;

import java.util.List;

public class OrderDetailActivity extends AppCompatActivity {

    ListView listView;
    @Override   
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        Intent intent = getIntent();
        User user =(User) intent.getSerializableExtra("USERINFO");
        TextView txtHouse = findViewById(R.id.txtHouseOrderDetail);
        TextView txtUser = findViewById(R.id.txtUserOrderDetail);


        List<OrderDetail> orderDetails = (List<OrderDetail>) intent.getSerializableExtra("DETAIL");
        OrderDetailAdapter adapter = new OrderDetailAdapter();
        txtUser.setText(user.getFullName());
        txtHouse.setText(user.getHouse().getHouseName());
        adapter.setOrderDetails(orderDetails);
        listView = findViewById(R.id.lvOrderDetail);
        listView.setAdapter(adapter);
    }
}
