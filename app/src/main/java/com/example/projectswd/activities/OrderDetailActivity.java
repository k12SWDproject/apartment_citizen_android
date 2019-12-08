package com.example.projectswd.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.projectswd.R;
import com.example.projectswd.adapter.OrderDetailAdapter;
import com.example.projectswd.model.OrderDetail;

import java.util.List;

public class OrderDetailActivity extends AppCompatActivity {

    ListView listView;
    @Override   
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        Intent intent = getIntent();

        List<OrderDetail> orderDetails = (List<OrderDetail>) intent.getSerializableExtra("DETAIL");
        OrderDetailAdapter adapter = new OrderDetailAdapter();
        adapter.setOrderDetails(orderDetails);
        listView = findViewById(R.id.lvOrderDetail);
        listView.setAdapter(adapter);
    }
}
