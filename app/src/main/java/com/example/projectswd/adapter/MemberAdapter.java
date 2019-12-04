package com.example.projectswd.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.projectswd.R;
import com.example.projectswd.model.User;

import java.util.List;

public class MemberAdapter  extends BaseAdapter {
    List<User>  listUser;

    public List<User> getListUser() {
        return listUser;
    }

    public void setListUser(List<User> listUser) {
        this.listUser = listUser;
    }

    public MemberAdapter(List<User> listUser) {
        this.listUser = listUser;
    }

    public MemberAdapter(){

    }

    @Override
    public int getCount() {
        return listUser.size();
    }

    @Override
    public Object getItem(int position) {
        return listUser.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listUser.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.card_member_view, parent, false);

        }
        TextView nameMember = convertView.findViewById(R.id.titleMember);
        nameMember.setText(listUser.get(position).getFullName()+"-"+listUser.get(position).getIdNumber());



        return convertView;
    }
}
