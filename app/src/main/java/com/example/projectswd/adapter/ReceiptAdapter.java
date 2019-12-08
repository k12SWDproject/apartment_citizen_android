package com.example.projectswd.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectswd.R;
import com.example.projectswd.model.ReceiptItem;

import java.util.List;

public class ReceiptAdapter extends BaseAdapter {
    private List<ReceiptItem> listReceipt;
    TextView txtSoNha;
    public ReceiptAdapter() {
    }

    public ReceiptAdapter(List<ReceiptItem> listReceipt) {
        this.listReceipt = listReceipt;
    }

    public List<ReceiptItem> getListReceipt() {
        return listReceipt;
    }

    public void setListReceipt(List<ReceiptItem> listReceipt) {
        this.listReceipt = listReceipt;
    }

    @Override
    public int getCount() {
        return listReceipt.size();
    }

    @Override
    public Object getItem(int position) {
        return listReceipt.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listReceipt.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.item_receipt, parent, false);
        }
        ReceiptItem receiptItem = listReceipt.get(position);
        TextView txtTenHoaDon = convertView.findViewById(R.id.txtTenHoaDon);

        TextView txtThanhGia = convertView.findViewById(R.id.txtThanhGia);

        txtTenHoaDon.setText(receiptItem.getReceiptName());


        txtThanhGia.setText("Thành Giá: "+ receiptItem.getTotal()+"");
        ImageView imageView = convertView.findViewById(R.id.btnThanhToan);
        if(receiptItem.getStatus()==1){
            imageView.setBackgroundResource(R.drawable.button_thanh_toan);
        }else {
            imageView.setBackgroundResource(R.drawable.btn_da_thanh_toan);

        }


        return convertView;
    }
}
