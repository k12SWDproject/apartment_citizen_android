package com.example.projectswd.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.projectswd.R;
import com.example.projectswd.model.Order;
import com.example.projectswd.model.OrderDTO;
import com.example.projectswd.model.OrderDetail;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.zip.Inflater;

public class OrderAdapter extends BaseAdapter {
    private List<OrderDTO> orderDTOS;

    public List<OrderDTO> getOrderDTOS() {
        return orderDTOS;
    }

    public void setOrderDTOS(List<OrderDTO> orderDTOS) {
        this.orderDTOS = orderDTOS;
    }

    @Override
    public int getCount() {
        return orderDTOS.size();
    }

    @Override
    public Object getItem(int position) {
        return orderDTOS.get(position);
    }

    @Override
    public long getItemId(int position) {
        return orderDTOS.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.item_order, parent, false);

        }
//        List<OrderDetail> orderDetails = orderDTOS.get(position).getOrderDetail();
//        OrderDetail orderDetail = orderDetails.get(position);
        Order order = getOrderDTOS().get(position).getOrder();
        TextView txtName = convertView.findViewById(R.id.txtNameProductOrder);
        TextView txtQuantity = convertView.findViewById(R.id.txtQuantityOfOrder);
        TextView total = convertView.findViewById(R.id.txtTotalOrder);
        String date = order.getCreateDate().substring(0,10);
        txtName.setText(date);
        if(order.getStatus()==0){
            txtQuantity.setText("Chưa nhận hàng");
        }else{
            txtQuantity.setText("Đã nhận hàng");
        }

        total.setText(order.getTotal()+"");

        return convertView;
    }
}
