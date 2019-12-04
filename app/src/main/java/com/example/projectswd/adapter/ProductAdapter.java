package com.example.projectswd.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.projectswd.R;
import com.example.projectswd.model.Product;

import java.util.List;

public class ProductAdapter extends BaseAdapter {
    List<Product> productList;

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return productList.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.item_product, parent, false);
        }

        Product product = productList.get(position);
        TextView txtNameProduct = convertView.findViewById(R.id.txtNameProduct);
        TextView txtPrice = convertView.findViewById(R.id.txtPriceProduct);

        txtNameProduct.setText(product.getName());
        txtPrice.setText(product.getPrice()+"");

        return convertView;
    }
}
