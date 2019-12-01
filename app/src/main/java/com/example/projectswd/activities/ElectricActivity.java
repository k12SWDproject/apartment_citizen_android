package com.example.projectswd.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectswd.R;
import com.example.projectswd.adapter.ElectricAdapter;
import com.example.projectswd.model.User;
import com.example.projectswd.presenters.GetListReciptPresenter;
import com.example.projectswd.model.HouseRecipt;
import com.example.projectswd.model.ReceiptItem;
import com.example.projectswd.views.GetListReciptView;

import java.util.List;

public class ElectricActivity extends AppCompatActivity implements GetListReciptView {

    ListView lvNotPayedReceipt, lvPayedReceipt;
    TextView txtUserinfo, txtReceitpsPayedNull, txtReceitpsNotPayNull;
    String token;
    User user;
    private HouseRecipt houseRecipts;
    private ElectricAdapter adapterPayed;
    private ElectricAdapter adapterNotPay;
    private GetListReciptPresenter getListReciptPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electric);
        Intent intent = getIntent();
        token = intent.getStringExtra("TOKEN");
        user = new User();
        user = (User) intent.getSerializableExtra("USERINFO");

        getListReciptPresenter = new GetListReciptPresenter(this);
        getListReciptPresenter.getList(token, "ELECTRIC_TYPE");


        lvPayedReceipt = findViewById(R.id.lvElectricReceiptsPayed);
        lvNotPayedReceipt = findViewById(R.id.lvElectricReceiptsNotPay);
        txtUserinfo = findViewById(R.id.txtUserInfoElect);
        txtReceitpsNotPayNull = findViewById(R.id.txtElectricReceiptNotPayNull);
        txtReceitpsPayedNull = findViewById(R.id.txtElectricReceiptPayedNull);


        txtUserinfo.setText(user.getHouse().getHouseName());
        lvPayedReceipt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        lvNotPayedReceipt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }



    @Override
    public void getListReciptSuccess(HouseRecipt houseRecipt) {
        houseRecipts = houseRecipt;
        showListNotPay();
        showListPayed();



    }

    @Override
    public void getListReciptFail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private  void showListPayed(){
        adapterPayed = new ElectricAdapter();
        List<ReceiptItem> listPayed = houseRecipts.getListPayedReceipt();
        if(listPayed.size()==0){
            txtReceitpsPayedNull.setVisibility(View.VISIBLE);
            txtReceitpsPayedNull.setText("Bạn không có hóa đơn đã thanh toán");
            lvPayedReceipt.setVisibility(View.GONE);
        }else{
            adapterPayed.setListReceipt(listPayed);

            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) lvPayedReceipt.getLayoutParams();
            lp.height = 257* listPayed.size();
            lvPayedReceipt.setLayoutParams(lp);
            lvPayedReceipt.setAdapter(adapterPayed);
            lvPayedReceipt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ReceiptItem receiptItem = (ReceiptItem) adapterPayed.getItem(position);
                    Intent intent = new Intent(getApplicationContext(), DetailPayedActivity.class);
                    intent.putExtra("ID", receiptItem.getReceiptId());
                    intent.putExtra("NAMEQUANTITY", "Điện năng tiêu thụ");
                    intent.putExtra("TOKEN", token);
                    intent.putExtra("USERINFO", user);
                    startActivity(intent);

                }
            });
        }
    }
    private void showListNotPay(){
        List<ReceiptItem> listNotPay = houseRecipts.getListNotPayedReceipt();
        adapterNotPay = new ElectricAdapter();
        if(listNotPay.size()==0){
            txtReceitpsNotPayNull.setVisibility(View.VISIBLE);
            txtReceitpsNotPayNull.setText("Bạn không có hóa đơn cần phải thanh toán");
            lvNotPayedReceipt.setVisibility(View.GONE);
        }else{
            adapterNotPay.setListReceipt(listNotPay);

            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) lvNotPayedReceipt.getLayoutParams();
            lp.height = 257 * listNotPay.size();
            lvNotPayedReceipt.setLayoutParams(lp);

            lvNotPayedReceipt.setAdapter(adapterNotPay);
            lvNotPayedReceipt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ReceiptItem receiptItem = (ReceiptItem) adapterNotPay.getItem(position);
                    Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                    intent.putExtra("ID", receiptItem.getReceiptId());
                    intent.putExtra("NAMEQUANTITY", "Điện năng tiêu thụ");
                    intent.putExtra("TOKEN", token);
                    intent.putExtra("USERINFO", user);
                    startActivity(intent);

                }
            });
        }
    }
}
