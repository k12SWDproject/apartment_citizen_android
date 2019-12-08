package com.example.projectswd.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectswd.R;
import com.example.projectswd.adapter.MemberAdapter;
import com.example.projectswd.adapter.MemberOutHomeAdapter;
import com.example.projectswd.contract.ManageMembersActivityContract;
import com.example.projectswd.model.FilterEmail;
import com.example.projectswd.model.FilterHouse;
import com.example.projectswd.model.House;
import com.example.projectswd.model.User;
import com.example.projectswd.presenters.ManageMembersActivityPresenter;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

public class ManageMembersActivity extends AppCompatActivity implements ManageMembersActivityContract.view {
    ListView listView;
    EditText edtSearch;
    List<User> userInHouse;
    Button  btnAdd, btnDelete;
    TextView txtMemberToAdd;
    LinearLayout linearLayout, lnMember;
    private House house;
    User userToAdd ;
    private MemberAdapter memberAdapter;
    private MemberOutHomeAdapter memberOutHomeAdapter;
    private String token;
    private ManageMembersActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_members);
        final Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("USERINFO");
        token = intent.getStringExtra("TOKEN");
        house = user.getHouse();
        userToAdd = new User();

        linearLayout = findViewById(R.id.lnMember);
        listView = findViewById(R.id.listManageMembers);
        edtSearch = findViewById(R.id.edtSearchMember);
        txtMemberToAdd = findViewById(R.id.titleMemberToAdd);
        userInHouse = new ArrayList<>();

        initPresenter();
        FilterHouse filterHouse = new FilterHouse();
        filterHouse.setHouseId(house.getId());
        presenter.getListMember(token,filterHouse);

        btnAdd = findViewById(R.id.btnAddHomeToAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPresenter();
                presenter.addMember(token,userToAdd.getUsername());
            }
        });
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(ManageMembersActivity.this, "aaaSdsadasdas", Toast.LENGTH_SHORT).show();
//            }
//
//        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Button btnDelete = view.findViewById(R.id.btnDeleteMember);
//                btnDelete.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Log.d("LOGGGG", "AAAAAAa");
//                    }
//                });
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//



    }

    private void initPresenter(){
        presenter = new ManageMembersActivityPresenter(this);
    }
    @Override
    public void getListMemberSuccess(List<User> users) {
        linearLayout.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
        userInHouse = users;
        memberAdapter = new MemberAdapter();
        memberAdapter.setListUser(users);
        listView.setAdapter(memberAdapter);
        listView.setVisibility(View.VISIBLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Aaaaaa", "onItemClick: aaaaaa");
            }
        });


    }

    @Override
    public void getListMemberFail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getMemberByEmailSuccess(List<User> users) {


            boolean flag = false;
            for(int i=0; i<userInHouse.size(); i++){
                if(userInHouse.get(i).getId()==users.get(0).getId()){
                    flag=true;
                    break;
                }
            }
            if(flag==true){
                memberAdapter = new MemberAdapter();
                memberAdapter.setListUser(users);
                listView.setAdapter(memberAdapter);
            }
           else{
//                memberOutHomeAdapter = new MemberOutHomeAdapter();
//                memberOutHomeAdapter.setListUser(users);
//                listView.setAdapter(memberOutHomeAdapter);
                txtMemberToAdd.setText(users.get(0).getFullName()+"-"+users.get(0).getIdNumber());
                linearLayout.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
                userToAdd = users.get(0);

            }



    }

    @Override
    public void getMemberByEmailFail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addMemberSuccess(ResponseBody responseBody) {
        initPresenter();
        FilterHouse filterHouse = new FilterHouse();
        filterHouse.setHouseId(house.getId());
        presenter.getListMember(token,filterHouse);
    }

    @Override
    public void addMemberFail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteMemberSuccess(ResponseBody responseBody) {
        initPresenter();
        FilterHouse filterHouse = new FilterHouse();
        filterHouse.setHouseId(house.getId());
        presenter.getListMember(token,filterHouse);
    }

    @Override
    public void deleteMemberFail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void clickToAddMember(View view) {
        initPresenter();
        String search = edtSearch.getText().toString();
        FilterEmail filterEmail = new FilterEmail();
        filterEmail.setEmail(search);
        presenter.getMemberByEmail(token,filterEmail);

    }
    public View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }
}
