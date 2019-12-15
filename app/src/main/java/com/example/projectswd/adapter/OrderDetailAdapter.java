package com.example.projectswd.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.projectswd.R;
import com.example.projectswd.model.Order;
import com.example.projectswd.model.OrderDetail;

import java.math.BigInteger;
import java.util.List;

public class OrderDetailAdapter extends BaseAdapter {

    private List<OrderDetail> orderDetails;

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public int getCount() {
        return orderDetails.size();
    }

    @Override
    public Object getItem(int position) {
        return orderDetails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return orderDetails.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.item_order_detail, parent, false);

        }
//        List<OrderDetail> orderDetails = orderDTOS.get(position).getOrderDetail();
//        OrderDetail orderDetail = orderDetails.get(position);
        OrderDetail order = orderDetails.get(position);
        TextView txtName = convertView.findViewById(R.id.txtNameOrderDetail);
        TextView txtQuantity = convertView.findViewById(R.id.txtQuantityOrderDetail);
        TextView total = convertView.findViewById(R.id.txtTotalOrderDetail);
//        product.getPrice().multiply(new BigInteger(quantity+"")).toString()

        txtName.setText(order.getName());
        txtQuantity.setText(order.getQuantity()+"");
//        total.setText(order.getPrice().multiply(new BigInteger((order.getQuantity()+"")).toString());
        total.setText(order.getPrice().multiply(new BigInteger(order.getQuantity()+"")).toString() + " VNĐ");
        return convertView;
    }
}
