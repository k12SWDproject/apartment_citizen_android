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
import com.example.projectswd.model.House;
import com.example.projectswd.model.User;
import com.example.projectswd.presenters.GetListReciptPresenter;
import com.example.projectswd.repositories.APIService;
import com.example.projectswd.model.HouseRecipt;
import com.example.projectswd.model.Receipts;
import com.example.projectswd.views.GetListReciptView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ElectricActivity extends AppCompatActivity implements GetListReciptView {

    ListView lvListNotPayedReceipt, lvListPayedReceipt;
    TextView txtUserinfo, txtHoaDonPhaiThanhToanNull, txtHoaDonDaThanhToanNull;
    String token;
    User user;
    private HouseRecipt houseRecipts;
    private ElectricAdapter adapter;
    private ElectricAdapter adapter2;
    private GetListReciptPresenter getListReciptPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electric);
        Intent intent = getIntent();
        token = intent.getStringExtra("TOKEN");
        user = (User) intent.getSerializableExtra("USERINFO");

        getListReciptPresenter = new GetListReciptPresenter(this);
        getListReciptPresenter.getList(token, "ELECTRIC_TYPE");


        lvListPayedReceipt = findViewById(R.id.lvDienDaThanhToan);
        lvListNotPayedReceipt = findViewById(R.id.lvDienChuaThanhToan);
        txtUserinfo = findViewById(R.id.txtUserInfo);
        txtHoaDonPhaiThanhToanNull = findViewById(R.id.txtHoaDonPhaiThanhToanNull);
        txtHoaDonDaThanhToanNull = findViewById(R.id.txtHoaDonDaThanhToanNull);


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
        if(houseRecipts.getListPayedReceipt().size()==0){
            txtHoaDonPhaiThanhToanNull.setVisibility(View.VISIBLE);
            txtHoaDonPhaiThanhToanNull.setText("Bạn không có hóa đơn cần thanh toán");
            lvListPayedReceipt.setVisibility(View.GONE);
        }else{
            adapter.setListReceipt(houseRecipts.getListPayedReceipt());
            lvListPayedReceipt.setAdapter(adapter);
        }
        if(houseRecipts.getListNotPayedReceipt().size()==0){
            txtHoaDonDaThanhToanNull.setVisibility(View.VISIBLE);
            txtHoaDonDaThanhToanNull.setText("Bạn không có hóa đơn đã thanh toán");
            lvListNotPayedReceipt.setVisibility(View.GONE);
        }else{
            adapter2.setListReceipt(houseRecipts.getListNotPayedReceipt());
            lvListNotPayedReceipt.setAdapter(adapter2);
        }



    }

    @Override
    public void getListReciptFail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


}
