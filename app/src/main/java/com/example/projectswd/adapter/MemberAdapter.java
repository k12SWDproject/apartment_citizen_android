package com.example.projectswd.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectswd.R;
import com.example.projectswd.activities.ManageMembersActivity;
import com.example.projectswd.activities.MenuActivity;
import com.example.projectswd.model.User;
import com.example.projectswd.utils.ClientApi;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemberAdapter  extends BaseAdapter {
    private List<User>  listUser;

    public List<User> getListUser() {
        return listUser;
    }

    public void setListUser(List<User> listUser) {
        this.listUser = listUser;
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
        return listUser.indexOf(getItem(position));
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.card_member_view, parent, false);

        }
        TextView nameMember = convertView.findViewById(R.id.titleMember);
        nameMember.setText(listUser.get(position).getFullName()+"-"+listUser.get(position).getUsername());
        Button btnDelete = convertView.findViewById(R.id.btnDeleteMember);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                api(listUser.get(position).getUsername(),position);
            }
        });



        return convertView;
    }
    private void api(String username, final int position){

        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> call = clientApi.APIService().deleteMember(MenuActivity.tokenTmp,username);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                listUser.remove(position);
                notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
