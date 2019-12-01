package com.example.projectswd.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
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
import com.example.projectswd.views.DetailReceiptView;
import com.example.projectswd.views.GetListReciptView;

import java.util.List;

public class WaterActivity extends AppCompatActivity implements GetListReciptView {

    ListView lvNotPayedReceipt, lvPayedReceipt;
    TextView txtUserinfo, txtWaterReceiptsPayedNull,txtWaterReceiptsNotPayNull;
    String token;
    ImageButton imgPay;
    List<ReceiptItem> listPayed,listNotPay;
    User user;
    private HouseRecipt houseRecipts;
    private ElectricAdapter adapterPayed;
    private ElectricAdapter adapterNotPay;
    private GetListReciptPresenter getListReciptPresenter;


    @Override
    protected void onStart() {
        super.onStart();
        getListReciptPresenter = new GetListReciptPresenter(this);
        getListReciptPresenter.getList(token, "WATER_TYPE");
        final Intent intent = getIntent();
        token = intent.getStringExtra("TOKEN");
        user = (User) intent.getSerializableExtra("USERINFO");
        txtUserinfo.setText(user.getHouse().getHouseName());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);





        lvPayedReceipt = findViewById(R.id.lvWaterReceiptsPayed);
        lvNotPayedReceipt = findViewById(R.id.lvWaterReceiptsNotPay);
        txtUserinfo = findViewById(R.id.txtUserInfoWater);
        txtWaterReceiptsPayedNull = findViewById(R.id.txtWaterReceiptsPayedNull);
        txtWaterReceiptsNotPayNull = findViewById(R.id.txtWaterReceiptsNotPayNull);
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
       listPayed = houseRecipts.getListPayedReceipt();
        if(listPayed.size()==0){
            txtWaterReceiptsPayedNull.setVisibility(View.VISIBLE);
            txtWaterReceiptsPayedNull.setText("Bạn không có hóa đơn đã thanh toán");
            lvPayedReceipt.setVisibility(View.GONE);
        }else{
            adapterPayed.setListReceipt(listPayed);

            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) lvPayedReceipt.getLayoutParams();
            lp.height = 300* listPayed.size();
            lvPayedReceipt.setLayoutParams(lp);
            lvPayedReceipt.setAdapter(adapterPayed);
            lvPayedReceipt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ReceiptItem receiptItem = (ReceiptItem) adapterPayed.getItem(position);
                Intent intent = new Intent(getApplicationContext(), DetailPayedActivity.class);
                    intent.putExtra("ID", receiptItem.getReceiptId());
                    intent.putExtra("NAMEQUANTITY", "Số khối đã sư dụng");
                intent.putExtra("TOKEN", token);
                intent.putExtra("USERINFO", user);
                intent.putExtra("ID", receiptItem.getReceiptId());
                startActivity(intent);

                }
            });
        }


    }
    private void showListNotPay(){
       listNotPay = houseRecipts.getListNotPayedReceipt();
        adapterNotPay = new ElectricAdapter();
        if(listNotPay.size()==0){
            txtWaterReceiptsNotPayNull.setVisibility(View.VISIBLE);
            txtWaterReceiptsNotPayNull.setText("Bạn không có hóa đơn cần phải thanh toán");
            lvNotPayedReceipt.setVisibility(View.GONE);
        }else{
            adapterNotPay.setListReceipt(listNotPay);

            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) lvNotPayedReceipt.getLayoutParams();
            lp.height = 300 * listNotPay.size();

            lvNotPayedReceipt.setLayoutParams(lp);


            lvNotPayedReceipt.setAdapter(adapterNotPay);
            lvNotPayedReceipt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ReceiptItem receiptItem = (ReceiptItem) adapterNotPay.getItem(position);
                    Intent intent = new Intent(getApplicationContext(), DetailActivity.class);

                    intent.putExtra("TOKEN", token);
                    intent.putExtra("USERINFO", user);
                    intent.putExtra("ID", receiptItem.getReceiptId());
                    intent.putExtra("NAMEQUANTITY", "Số khối đã sư dụng");
                    startActivity(intent);

                }
            });
        }

    }
}
