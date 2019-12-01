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
import com.example.projectswd.model.HouseRecipt;
import com.example.projectswd.model.ReceiptItem;
import com.example.projectswd.model.User;
import com.example.projectswd.presenters.GetListReciptPresenter;
import com.example.projectswd.views.GetListReciptView;

import java.util.List;

public class OrtherActivity extends AppCompatActivity implements GetListReciptView {

    ListView lvNotPayedReceipt, lvPayedReceipt;
    TextView txtUserinfo, txtOrtherPhaiThanhToanNull,txtOrtherDaThanhToanNull;
    String token;
    User user;
    private HouseRecipt houseRecipts;
    private ElectricAdapter adapterPayed;
    private ElectricAdapter adapterNotPay;
    private GetListReciptPresenter getListReciptPresenter;

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        token = intent.getStringExtra("TOKEN");
        user = (User) intent.getSerializableExtra("USERINFO");

        getListReciptPresenter = new GetListReciptPresenter(this);
        getListReciptPresenter.getList(token, "ORTHER_TYPE");
        txtUserinfo.setText(user.getHouse().getHouseName());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orther);


        lvPayedReceipt = findViewById(R.id.lvOrtherReceiptsPayed);
        lvNotPayedReceipt = findViewById(R.id.lvOrtherReceiptsNotPay);
        txtUserinfo = findViewById(R.id.txtUserInfoOrther);
        txtOrtherDaThanhToanNull = findViewById(R.id.txtOrtherReceiptsPayedNull);
        txtOrtherPhaiThanhToanNull = findViewById(R.id.txtOrtherReceiptsNotPayNull);
        onStart();




    }

    @Override
    public void getListReciptSuccess(HouseRecipt houseRecipt) {

        houseRecipts = houseRecipt;
        showListPayed();
       showListNotPay();
    }

    @Override
    public void getListReciptFail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private  void showListPayed(){
        adapterPayed = new ElectricAdapter();
        List<ReceiptItem> listPayed = houseRecipts.getListPayedReceipt();
        if(listPayed.size()==0){
            txtOrtherDaThanhToanNull.setVisibility(View.VISIBLE);
            txtOrtherDaThanhToanNull.setText("Bạn không có hóa đơn đã thanh toán");
            lvPayedReceipt.setVisibility(View.GONE);
        }else{
            adapterPayed.setListReceipt(listPayed);

            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) lvPayedReceipt.getLayoutParams();
            lp.height = 265* listPayed.size();
            
            lvPayedReceipt.setLayoutParams(lp);
            lvPayedReceipt.setAdapter(adapterPayed);
            lvPayedReceipt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ReceiptItem receiptItem = (ReceiptItem) adapterPayed.getItem(position);
                    Intent intent = new Intent(getApplicationContext(), DetailPayedActivity.class);
                    intent.putExtra("ID", receiptItem.getReceiptId());
                    intent.putExtra("NAMEQUANTITY", "");
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
            txtOrtherPhaiThanhToanNull.setVisibility(View.VISIBLE);
            txtOrtherPhaiThanhToanNull.setText("Bạn không có hóa đơn cần phải thanh toán");
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
                    intent.putExtra("NAMEQUANTITY", "Số tháng (Số lần): ");
                    intent.putExtra("TOKEN", token);
                    intent.putExtra("USERINFO", user);
                    startActivity(intent);

                }
            });
        }
    }
}
