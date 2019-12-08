package com.example.projectswd.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.projectswd.R;
import com.example.projectswd.activities.MenuActivity;
import com.example.projectswd.model.CartObject;
import com.example.projectswd.model.Product;

import java.math.BigInteger;
import java.util.List;

public class CartAdapter extends BaseAdapter {

    List<CartObject> cartObjects;

    public void setCartObjects(List<CartObject> cartObjects) {
        this.cartObjects = cartObjects;
    }



    @Override
    public int getCount() {
        return cartObjects.size();
    }

    @Override
    public Object getItem(int position) {
        return cartObjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return cartObjects.indexOf(getItem(position));
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.item_cart, parent, false);
        }

        final CartObject product = cartObjects.get(position);
        TextView txtNameProduct = convertView.findViewById(R.id.txtNameProduct);
        final TextView txtPrice = convertView.findViewById(R.id.txtPriceProduct);
        final TextView txtQuantity = convertView.findViewById(R.id.txtQuantityOfCart);
        Button btnAdd = convertView.findViewById(R.id.btnAdd);
        Button btnSub = convertView.findViewById(R.id.btnSub);
        Button btnRemove = convertView.findViewById(R.id.btnRemove);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quan = txtQuantity.getText().toString();
                int quantity = Integer.parseInt(quan) +1;
                txtQuantity.setText(quantity+"");
                MenuActivity.productList.get(position).setQuantityOfCart(quantity);
                txtPrice.setText(product.getPrice().multiply(new BigInteger(quantity+"")).toString() + " VNĐ");
                notifyDataSetChanged();
            }
        });
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quan = txtQuantity.getText().toString();
                int quantity = Integer.parseInt(quan);
                if (quantity>1){
                    quantity = quantity-1;
                }
                txtQuantity.setText(quantity+"");
                MenuActivity.productList.get(position).setQuantityOfCart(quantity);
                txtPrice.setText(product.getPrice().multiply(new BigInteger(quantity+"")).toString()+ " VNĐ");
                notifyDataSetChanged();
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuActivity.productList.remove(MenuActivity.productList.get(position));
                notifyDataSetChanged();
            }
        });

        txtNameProduct.setText(product.getName());

        txtPrice.setText(  MenuActivity.productList.get(position).getPrice().multiply(new BigInteger(MenuActivity.productList.get(position).getQuantityOfCart()+"")).toString() + " VNĐ");
        txtQuantity.setText(MenuActivity.productList.get(position).getQuantityOfCart()+"");
        return convertView;
    }
}
