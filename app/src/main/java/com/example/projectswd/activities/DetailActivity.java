package com.example.projectswd.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectswd.R;
import com.example.projectswd.model.FilterObj;
import com.example.projectswd.model.HouseRecipt;
import com.example.projectswd.model.Receipt;
import com.example.projectswd.model.ReceiptDTO;
import com.example.projectswd.model.ReceiptItem;
import com.example.projectswd.model.User;
import com.example.projectswd.presenters.DetailReceiptPresenter;
import com.example.projectswd.presenters.ReceiptPayPresenter;
import com.example.projectswd.views.DetailReceiptView;
import com.example.projectswd.views.ReceiptPayView;

public class DetailActivity extends AppCompatActivity implements DetailReceiptView, ReceiptPayView {

    private String token, id, nameQuantity;

    private User user;

    TextView txtIdRecipt, txtFullname,txtDateOfReceipt, txtNameOfQuantity, txtQuantity, txtMoney, txtUserInfo, txtNameReceipt;
    private DetailReceiptPresenter detailReceiptPresenter;
    private ReceiptPayPresenter receiptPayPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        token = intent.getStringExtra("TOKEN");
        user =(User) intent.getSerializableExtra("USERINFO");
        nameQuantity = intent.getStringExtra("NAMEQUANTITY");

        txtIdRecipt = findViewById(R.id.txtIdReceipt);
        txtFullname = findViewById(R.id.txtFullname);
        txtDateOfReceipt = findViewById(R.id.txtDateOfReceipt);
        txtNameOfQuantity = findViewById(R.id.txtNameOfQuantity);
        txtQuantity = findViewById(R.id.txtQuantity);
        txtMoney = findViewById(R.id.txtMoneyOfReceipt);
        txtUserInfo = findViewById(R.id.txtUserInfoDetail);
        txtNameReceipt = findViewById(R.id.txtNameReceipt);


        detailReceiptPresenter = new DetailReceiptPresenter(this);

        FilterObj filterObj = new FilterObj();
        filterObj.setId(Integer.parseInt(id));
        detailReceiptPresenter.getDetailReceipt(token,filterObj);



    }


    @Override
    public void success(ReceiptDTO receipt) {
        txtIdRecipt.setText(receipt.getReceipt().getId()+"");
        txtMoney.setText(receipt.getReceiptDetailList().get(0).getTotal()+"");
        txtFullname.setText(user.getFullName());
        txtDateOfReceipt.setText(receipt.getReceipt().getPublishDate()+"");
        txtQuantity.setText(receipt.getReceiptDetailList().get(0).getQuantity()+"");
        txtNameOfQuantity.setText(nameQuantity);
        txtNameReceipt.setText(receipt.getReceipt().getTitle());
        txtUserInfo.setText(user.getHouse().getHouseName());

    }

    @Override
    public void success(String string) {
        onBackPressed();
    }

    @Override
    public void fail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }



    public void clickToPay(View view) {
        receiptPayPresenter = new ReceiptPayPresenter(this);
        receiptPayPresenter.payReceipt(token, Integer.parseInt(id));


    }
}
