package com.example.projectswd.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.projectswd.R;
import com.example.projectswd.adapter.CartAdapter;
import com.example.projectswd.adapter.ProductAdapter;
import com.example.projectswd.model.CartObject;
import com.example.projectswd.model.Product;
import com.google.gson.Gson;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    List<Product> products;
    List<CartObject> cartObjects;
    TextView txtTotal;
    ListView lvProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        CartAdapter adapter = new CartAdapter();

        lvProduct = findViewById(R.id.lvProductOfCart);
        txtTotal = findViewById(R.id.txtTotal);
        adapter.setCartObjects(MenuActivity.productList);
        lvProduct.setAdapter(adapter);
       BigInteger sum = new BigInteger("0") ;
        for (int i = 0; i<MenuActivity.productList.size();i++){
           sum = sum.add(MenuActivity.productList.get(i).getPrice().multiply(new BigInteger(MenuActivity.productList.get(i).getQuantityOfCart()+"")));
        }
        txtTotal.setText("Tổng tiền:"+sum.toString()+ " VNĐ");
        lvProduct.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                BigInteger sumtotal = new BigInteger("0") ;
                for (int i = 0; i<MenuActivity.productList.size();i++){
                    sumtotal = sumtotal.add(MenuActivity.productList.get(i).getPrice().multiply(new BigInteger(MenuActivity.productList.get(i).getQuantityOfCart()+"")));
                }
                txtTotal.setText("Tổng tiền:"+sumtotal.toString()+ " VNĐ");
            }
        });


//        lvProduct.deferNotifyDataSetChanged();

    }

    public void clickToPayOrder(View view) {
        Gson gson = new Gson();

        String json = gson.toJson(gson);


    }
}
