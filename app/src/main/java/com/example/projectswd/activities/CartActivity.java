package com.example.projectswd.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectswd.R;
import com.example.projectswd.adapter.CartAdapter;
import com.example.projectswd.adapter.ProductAdapter;
import com.example.projectswd.contract.CartActivityContract;
import com.example.projectswd.model.CartObject;
import com.example.projectswd.model.PayObject;
import com.example.projectswd.model.Product;
import com.example.projectswd.presenters.CartActivityPresenter;
import com.google.gson.Gson;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

public class CartActivity extends AppCompatActivity implements CartActivityContract.view {
    List<Product> products;
    List<CartObject> cartObjects;
    TextView txtTotal;
    ListView lvProduct;
    private CartActivityPresenter cartActivityPresenter;
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        CartAdapter adapter = new CartAdapter();
        Intent intent = getIntent();
        token = intent.getStringExtra("TOKEN");
        lvProduct = findViewById(R.id.lvProductOfCart);
        txtTotal = findViewById(R.id.txtTotal);
        adapter.setCartObjects(MenuActivity.productList);
        lvProduct.setAdapter(adapter);
       BigInteger sum = new BigInteger("0") ;
        for (int i = 0; i<MenuActivity.productList.size();i++){
           sum = sum.add(MenuActivity.productList.get(i).getPrice().multiply(new BigInteger(MenuActivity.productList.get(i).getQuantityOfCart()+"")));
        }
        txtTotal.setText("Tổng tiền:"+sum.toString());
        lvProduct.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                BigInteger sumtotal = new BigInteger("0") ;
                for (int i = 0; i<MenuActivity.productList.size();i++){
                    sumtotal = sumtotal.add(MenuActivity.productList.get(i).getPrice().multiply(new BigInteger(MenuActivity.productList.get(i).getQuantityOfCart()+"")));
                }
                txtTotal.setText("Tổng tiền:"+sumtotal.toString());
            }
        });

        initPresenter();

//        lvProduct.deferNotifyDataSetChanged();

    }

    private void initPresenter(){
        cartActivityPresenter = new CartActivityPresenter(this);
    }

    public void clickToPayOrder(View view) {

       List<PayObject> payObjectList = new ArrayList<>();
       PayObject payObject;
       for (int i = 0; i <MenuActivity.productList.size();i++){
           payObject= new PayObject();
           payObject.setId(MenuActivity.productList.get(i).getId());
           payObject.setQuantity(MenuActivity.productList.get(i).getQuantityOfCart());
           payObjectList.add(payObject);
       }
       Gson gson = new Gson();
       String json = gson.toJson(payObjectList);
        Log.d("LGOOOOOOOOOOOOOOOOA", json);
       cartActivityPresenter.payment(token,payObjectList);


    }



    @Override
    public void paymentSuccess(ResponseBody responseBody) {

        Toast.makeText(this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
        onBackPressed();

    }

    @Override
    public void paymentFail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
