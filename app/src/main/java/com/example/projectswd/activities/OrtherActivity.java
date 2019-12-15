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
import com.example.projectswd.contract.OrtherActivityContract;
import com.example.projectswd.model.HouseRecipt;
import com.example.projectswd.model.ReceiptItem;
import com.example.projectswd.model.User;
import com.example.projectswd.presenters.OrtherActivityPresenter;

import java.util.List;

public class OrtherActivity extends AppCompatActivity implements OrtherActivityContract.view {

    ListView lvNotPayedReceipt, lvPayedReceipt;
    TextView txtUserinfo, txtOrtherPhaiThanhToanNull, txtOrtherDaThanhToanNull;
    String token;
    User user;
    private HouseRecipt houseRecipts;
    private ReceiptAdapter adapterPayed;
    private ReceiptAdapter adapterNotPay;
   private OrtherActivityPresenter presenter;

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        token = intent.getStringExtra("TOKEN");
        user = (User) intent.getSerializableExtra("USERINFO");

        initPresenter();
        presenter.getListOrtherReceipt(token,"ORTHER_TYPE");
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


    }


    private void initPresenter(){
        presenter = new OrtherActivityPresenter(this);
    }

    private void showListPayed() {
        adapterPayed = new ReceiptAdapter();
        List<ReceiptItem> listPayed = houseRecipts.getListPayedReceipt();
        if (listPayed.size() == 0) {
            txtOrtherDaThanhToanNull.setVisibility(View.VISIBLE);
            txtOrtherDaThanhToanNull.setText("Bạn không có hóa đơn đã thanh toán");
            lvPayedReceipt.setVisibility(View.GONE);
        } else {
            adapterPayed.setListReceipt(listPayed);

            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) lvPayedReceipt.getLayoutParams();
            lp.height = 200 * listPayed.size();

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

    private void showListNotPay() {
        List<ReceiptItem> listNotPay = houseRecipts.getListNotPayedReceipt();
        adapterNotPay = new ReceiptAdapter();
        if (listNotPay.size() == 0) {
            txtOrtherPhaiThanhToanNull.setVisibility(View.VISIBLE);
            txtOrtherPhaiThanhToanNull.setText("Bạn không có hóa đơn cần phải thanh toán");
            lvNotPayedReceipt.setVisibility(View.GONE);
        } else {
            adapterNotPay.setListReceipt(listNotPay);

            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) lvNotPayedReceipt.getLayoutParams();
            lp.height = 200 * listNotPay.size();
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

    @Override
    public void getListOrtherReceiptSuccess(HouseRecipt houseRecipt) {

        houseRecipts = houseRecipt;
        showListNotPay();
        showListPayed();
    }

    @Override
    public void getListOrtherReceiptFail(String msg) {

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
