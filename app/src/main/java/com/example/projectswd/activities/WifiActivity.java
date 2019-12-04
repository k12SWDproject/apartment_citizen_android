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
import com.example.projectswd.adapter.ReceiptAdapter;
import com.example.projectswd.contract.WifiActivityContract;
import com.example.projectswd.model.HouseRecipt;
import com.example.projectswd.model.ReceiptItem;
import com.example.projectswd.model.User;
import com.example.projectswd.presenters.WifiActivityPresenter;

import java.util.List;

public class WifiActivity extends AppCompatActivity implements WifiActivityContract.view {


    ListView lvNotPayedReceipt, lvPayedReceipt;
    TextView txtUserinfo, txtWifiReceitpsPayedNull,txtWifiReceitpsNotPayNull;
    String token;
    User user;
    private HouseRecipt houseRecipts;
    private ReceiptAdapter adapterPayed;
    private ReceiptAdapter adapterNotPay;
   private WifiActivityPresenter presenter;

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        token = intent.getStringExtra("TOKEN");
        user = (User) intent.getSerializableExtra("USERINFO");
//        getListReciptPresenter = new GetListReciptPresenter(this);
//        getListReciptPresenter.getList(token, "WIFI_TYPE");
        initPresenter();
        presenter.getListWifiReceipt(token,"WIFI_TYPE");
        txtUserinfo.setText(user.getHouse().getHouseName());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);





        lvPayedReceipt = findViewById(R.id.lvWifiReceiptsPayed);
        lvNotPayedReceipt = findViewById(R.id.lvWifiReceiptsNotPay);
        txtUserinfo = findViewById(R.id.txtUserInfoWifi);
        txtWifiReceitpsPayedNull = findViewById(R.id.txtWifiReceiptsPayedNull);
        txtWifiReceitpsNotPayNull = findViewById(R.id.txtWifiReceiptsNotPayNull);





    }

    private void initPresenter(){
        presenter = new WifiActivityPresenter(this);
    }

    private  void showListPayed(){
        adapterPayed = new ReceiptAdapter();
        List<ReceiptItem> listPayed = houseRecipts.getListPayedReceipt();
        if(listPayed.size()==0){
            txtWifiReceitpsPayedNull.setVisibility(View.VISIBLE);
            txtWifiReceitpsPayedNull.setText("Bạn không có hóa đơn đã thanh toán");
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
                    intent.putExtra("NAMEQUANTITY", "Số tháng: ");
                    intent.putExtra("TOKEN", token);
                    intent.putExtra("USERINFO", user);
                    startActivity(intent);

                }
            });
        }
    }
    private void showListNotPay(){
        List<ReceiptItem> listNotPay = houseRecipts.getListNotPayedReceipt();
        adapterNotPay = new ReceiptAdapter();
        if(listNotPay.size()==0){
            txtWifiReceitpsNotPayNull.setVisibility(View.VISIBLE);
            txtWifiReceitpsNotPayNull.setText("Bạn không có hóa đơn cần phải thanh toán");
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
                    intent.putExtra("NAMEQUANTITY", "Số tháng: ");
                    intent.putExtra("TOKEN", token);
                    intent.putExtra("USERINFO", user);
                    startActivity(intent);

                }
            });
        }
    }

    @Override
    public void getListWifiReceiptSuccess(HouseRecipt houseRecipt) {
        houseRecipts = houseRecipt;
        showListPayed();
        showListNotPay();
    }

    @Override
    public void getListWifiReceiptFail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
