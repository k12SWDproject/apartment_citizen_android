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
import com.example.projectswd.contract.ElectricActivityContract;
import com.example.projectswd.model.User;
import com.example.projectswd.presenters.ElectricActivityPresenter;
import com.example.projectswd.model.HouseRecipt;
import com.example.projectswd.model.ReceiptItem;

import java.util.List;

public class ElectricActivity extends AppCompatActivity implements ElectricActivityContract.view {

    ListView lvNotPayedReceipt, lvPayedReceipt;
    TextView txtUserinfo, txtReceitpsPayedNull, txtReceitpsNotPayNull;
    String token;
    User user;
    private HouseRecipt houseRecipts;
    private ReceiptAdapter adapterPayed;
    private ReceiptAdapter adapterNotPay;
//

    private ElectricActivityPresenter presenter;

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        token = intent.getStringExtra("TOKEN");
        user = new User();
        user = (User) intent.getSerializableExtra("USERINFO");

//        getListReciptPresenter = new GetListReciptPresenter(this);

//        getListReciptPresenter.getList(token, "ELECTRIC_TYPE");
        initPresenter();
        presenter.getListElectricReceipt(token,"ELECTRIC_TYPE");

        txtUserinfo.setText(user.getHouse().getHouseName());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electric);





        lvPayedReceipt = findViewById(R.id.lvElectricReceiptsPayed);
        lvNotPayedReceipt = findViewById(R.id.lvElectricReceiptsNotPay);
        txtUserinfo = findViewById(R.id.txtUserInfoElect);
        txtReceitpsNotPayNull = findViewById(R.id.txtElectricReceiptNotPayNull);
        txtReceitpsPayedNull = findViewById(R.id.txtElectricReceiptPayedNull);





    }

    private void initPresenter(){
        presenter = new ElectricActivityPresenter(this);
    }

//    @Override
//    public void getListReciptSuccess(HouseRecipt houseRecipt) {
//        houseRecipts = houseRecipt;
//        showListNotPay();
//        showListPayed();
//
//
//
//    }
//
//    @Override
//    public void getListReciptFail(String msg) {
//        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
//    }

    private  void showListPayed(){
        adapterPayed = new ReceiptAdapter();
        List<ReceiptItem> listPayed = houseRecipts.getListPayedReceipt();
        if(listPayed.size()==0){
            txtReceitpsPayedNull.setVisibility(View.VISIBLE);
            txtReceitpsPayedNull.setText("Bạn không có hóa đơn đã thanh toán");
            lvPayedReceipt.setVisibility(View.GONE);
        }else{
            adapterPayed.setListReceipt(listPayed);

            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) lvPayedReceipt.getLayoutParams();
            lp.height = 500* listPayed.size();
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
        adapterNotPay = new ReceiptAdapter();
        if(listNotPay.size()==0){
            txtReceitpsNotPayNull.setVisibility(View.VISIBLE);
            txtReceitpsNotPayNull.setText("Bạn không có hóa đơn cần phải thanh toán");
            lvNotPayedReceipt.setVisibility(View.GONE);
        }else{
            adapterNotPay.setListReceipt(listNotPay);

            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) lvNotPayedReceipt.getLayoutParams();
            lp.height = 500 * listNotPay.size();
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

    @Override
    public void getListElectricReceiptSuccess(HouseRecipt houseRecipt) {
        houseRecipts = houseRecipt;
        showListPayed();
        showListNotPay();

    }

    @Override
    public void getListElectricReceiptFail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
