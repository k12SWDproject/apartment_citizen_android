package com.example.projectswd.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.projectswd.R;
import com.example.projectswd.model.HouseRecipt;
import com.example.projectswd.model.Receipts;

import java.util.List;

public class ElectricAdapter extends BaseAdapter {
    private List<Receipts> listReceipt;

    public ElectricAdapter() {
    }

    public ElectricAdapter(List<Receipts> listReceipt) {
        this.listReceipt = listReceipt;
    }

    public List<Receipts> getListReceipt() {
        return listReceipt;
    }

    public void setListReceipt(List<Receipts> listReceipt) {
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
            convertView = inflater.inflate(R.layout.item_electric, parent, false);
        }
        TextView txtTenHoaDon = convertView.findViewById(R.id.txtTenHoaDon);
        TextView txtSoNha = convertView.findViewById(R.id.txtSonha);
        TextView txtSoDien = convertView.findViewById(R.id.txtSodienSuDung);
        TextView txtThanhGia = convertView.findViewById(R.id.txtThanhGia);

        txtTenHoaDon.setText(listReceipt.get(position).getReceiptName());
        txtSoNha.setText("ABC-123");
        txtSoDien.setText("Số điện: "+listReceipt.get(position).getQuantity()+"");
        txtThanhGia.setText("Thành Giá: "+listReceipt.get(position).getTotal()+"");
        ImageButton imgButton = convertView.findViewById(R.id.btnThanhToan);
        if(listReceipt.get(position).getStatus()==1){
            imgButton.setBackgroundResource(R.drawable.button_thanh_toan);
        }else {
            imgButton.setBackgroundResource(R.drawable.btn_da_thanh_toan);
        }


        return convertView;
    }
}
