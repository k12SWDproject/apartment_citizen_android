package com.example.projectswd.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectswd.R;
import com.example.projectswd.contract.DetailActivityContract;
import com.example.projectswd.model.FilterObj;
import com.example.projectswd.model.ReceiptDTO;
import com.example.projectswd.model.User;
import com.example.projectswd.presenters.DetailActivityPresenter;

import java.text.SimpleDateFormat;

public class DetailActivity extends AppCompatActivity implements DetailActivityContract.view {

    private String token, id, nameQuantity;

    private User user;

    TextView txtIdRecipt, txtFullname,txtDateOfReceipt, txtNameOfQuantity, txtQuantity, txtMoney, txtUserInfo, txtNameReceipt;
//    private DetailReceiptPresenter detailReceiptPresenter;
    private DetailActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        token = intent.getStringExtra("TOKEN");
        user =(User) intent.getSerializableExtra("USERINFO");
        nameQuantity = intent.getStringExtra("NAMEQUANTITY");
        nameQuantity.toUpperCase();
        txtIdRecipt = findViewById(R.id.txtIdReceipt);
        txtFullname = findViewById(R.id.txtFullname);
        txtDateOfReceipt = findViewById(R.id.txtDateOfReceipt);
        txtNameOfQuantity = findViewById(R.id.txtNameOfQuantity);
        txtQuantity = findViewById(R.id.txtQuantity);
        txtMoney = findViewById(R.id.txtMoneyOfReceipt);
        txtUserInfo = findViewById(R.id.txtUserInfoDetail);
        txtNameReceipt = findViewById(R.id.txtNameReceipt);


        initPresenter();

        FilterObj filterObj = new FilterObj();
        filterObj.setId(Integer.parseInt(id));
        presenter.showDetail(token,filterObj);



    }
    private void initPresenter(){
        presenter = new DetailActivityPresenter(this);
    }





    public void clickToPay(View view) {
//
        initPresenter();
        presenter.payReceipt(token,Integer.parseInt(id));

    }

    @Override
    public void showDetailSuccess(ReceiptDTO receipt) {
        txtIdRecipt.setText(receipt.getReceipt().getId()+"");
        txtMoney.setText(receipt.getReceiptDetailList().get(0).getTotal()+"");
        txtFullname.setText(user.getFullName());
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sf.format(receipt.getReceipt().getPublishDate());
        txtDateOfReceipt.setText(date);
        txtQuantity.setText(receipt.getReceiptDetailList().get(0).getQuantity()+"");
        txtNameOfQuantity.setText(nameQuantity.toUpperCase());
        txtNameReceipt.setText(receipt.getReceipt().getTitle());
        txtUserInfo.setText(user.getHouse().getHouseName());
    }

    @Override
    public void showDetailFail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void payReceiptSuccess(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    @Override
    public void payReceiptFail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
