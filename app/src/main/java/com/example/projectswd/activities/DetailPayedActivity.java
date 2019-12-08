package com.example.projectswd.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectswd.R;
import com.example.projectswd.contract.DetailPayedActivityContract;
import com.example.projectswd.model.FilterObj;
import com.example.projectswd.model.ReceiptDTO;
import com.example.projectswd.model.User;
import com.example.projectswd.presenters.DetailPayedActivityPresenter;
//import com.example.projectswd.presenters.DetailReceiptPresenter;

public class DetailPayedActivity extends AppCompatActivity implements DetailPayedActivityContract.view {

    private String token, id;

    private User user;

    TextView txtIdRecipt, txtFullname,txtDateOfReceipt, txtNameOfQuantity, txtQuantity, txtMoney, txtUserInfo, txtNameReceipt;
    private DetailPayedActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_payed);
        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        token = intent.getStringExtra("TOKEN");
        user =(User) intent.getSerializableExtra("USERINFO");

        initPresenter();
        txtIdRecipt = findViewById(R.id.txtIdReceiptPayed);
        txtFullname = findViewById(R.id.txtFullnamePayed);
        txtDateOfReceipt = findViewById(R.id.txtDateOfReceiptPayed);
        txtNameOfQuantity = findViewById(R.id.txtNameOfQuantityPayed);
        txtQuantity = findViewById(R.id.txtQuantityPayed);
        txtMoney = findViewById(R.id.txtMoneyOfReceiptPayed);
        txtUserInfo = findViewById(R.id.txtUserInfoPayed);
        txtNameReceipt = findViewById(R.id.txtNameReceiptPayed);


//        detailReceiptPresenter = new DetailReceiptPresenter(this);

        FilterObj filterObj = new FilterObj();
        filterObj.setId(Integer.parseInt(id));
        presenter.showDetailPayed(token,filterObj);
//        detailReceiptPresenter.getDetailReceipt(token,filterObj);
    }
    private void initPresenter(){
        presenter = new DetailPayedActivityPresenter(this);
    }
    @Override
    public void showDetailSuccessPayed(ReceiptDTO receipt) {
        txtIdRecipt.setText(receipt.getReceipt().getId()+"");
        txtMoney.setText(receipt.getReceiptDetailList().get(0).getTotal()+"");
        txtFullname.setText(user.getFullName());
        txtDateOfReceipt.setText(receipt.getReceipt().getPaymentDate()+"");
        txtQuantity.setText(receipt.getReceiptDetailList().get(0).getQuantity()+"");
        txtNameOfQuantity.setText("Điện năng tiêu thụ");
        txtNameReceipt.setText(receipt.getReceipt().getTitle());
        txtUserInfo.setText(user.getHouse().getHouseName());
    }

    @Override
    public void showDetailFailPayed(String msg) {

    }

//    @Override
//    public void success(ReceiptDTO receipt) {
//        txtIdRecipt.setText(receipt.getReceipt().getId()+"");
//        txtMoney.setText(receipt.getReceiptDetailList().get(0).getTotal()+"");
//        txtFullname.setText(user.getFullName());
//        txtDateOfReceipt.setText(receipt.getReceipt().getPaymentDate()+"");
//        txtQuantity.setText(receipt.getReceiptDetailList().get(0).getQuantity()+"");
//        txtNameOfQuantity.setText("Điện năng tiêu thụ");
//        txtNameReceipt.setText(receipt.getReceipt().getTitle());
//        txtUserInfo.setText(user.getHouse().getHouseName());
//    }
//
//    @Override
//    public void fail(String msg) {
//        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
//    }
}
