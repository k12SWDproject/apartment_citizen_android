package com.example.projectswd.fragments;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectswd.R;
import com.example.projectswd.activities.ManageMembersActivity;
import com.example.projectswd.contract.ProfileActivityContract;
import com.example.projectswd.model.House;
import com.example.projectswd.model.User;
import com.example.projectswd.model.UserUpdateDTO;
import com.example.projectswd.presenters.ProfileActivityPresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements ProfileActivityContract.view, DatePickerDialog.OnDateSetListener{


    public ProfileFragment() {
        // Required empty public constructor
    }

    EditText edtFullname, edtEmail, edtPhoneNo, edtBirthdate;
    TextView titleName,ApartTitleNo;
    Button edtBtn, updateBtn, btnManage;
    Spinner spinnerGender;
    private House house;
    private String token;
    private User user;
    private ProfileActivityPresenter presenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_profile, container, false);
        // Inflate the layout for this fragment
        final Intent intent = getActivity().getIntent();
        user = (User) intent.getSerializableExtra("USERINFO");
        token = intent.getStringExtra("TOKEN");
        house = user.getHouse();

        edtFullname = view.findViewById(R.id.edtFullname);
        spinnerGender = view.findViewById(R.id.spnGender);
        List<String> gender = new ArrayList<>();

        gender.add("Nam");
        gender.add("Nữ");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, gender);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(dataAdapter);

        edtEmail = view.findViewById(R.id.edtEmail);
        edtPhoneNo = view.findViewById(R.id.edtNumberphone);
        edtBirthdate = view.findViewById(R.id.edtBirthdate);
//        titleName = view.findViewById(R.id.titleName);
//        ApartTitleNo = view.findViewById(R.id.numberApartment);
        edtBtn = view.findViewById(R.id.editButton);
        updateBtn = view.findViewById(R.id.updateButton);
        btnManage = view.findViewById(R.id.btnManage);

        btnManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity(), ManageMembersActivity.class);
                intent1.putExtra("USERINFO",user);
                intent1.putExtra("TOKEN",token);
                startActivity(intent1);


            }
        });



        edtFullname.setText(user.getFullName());
        if(user.getGender() ==1){
//            spinnerGender.setAdapter();
            spinnerGender.setSelection(dataAdapter.getPosition("Nam"));


        }else{
            spinnerGender.setSelection(dataAdapter.getPosition("Nữ"));

        }
//        spinnerGender.setsetText(user.getGender() == 1 ? "Nam" : "Nữ");
        edtEmail.setText(user.getUsername());
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        edtBirthdate.setText(sf.format(user.getDateOfBirth()));
        edtPhoneNo.setText(user.getIdNumber() + "");
//        titleName.setText(user.getFullName());
//        ApartTitleNo.setText(house.getHouseName());
        return view;
    }

        private void initPresenter(){
        presenter = new ProfileActivityPresenter(this);
    }

        public void clickToEdit(View view) {
        edtFullname.setFocusable(true);
        edtFullname.setEnabled(true);

        updateBtn.setVisibility(View.VISIBLE);
        edtBtn.setVisibility(View.INVISIBLE);

    }

    public void clicktoUpdateProfile(View view)  {
        String fullname = edtFullname.getText().toString();
        String birthdate = edtBirthdate.getText().toString();
        int gender = 0;
        if(spinnerGender.getAdapter().equals("Nam")){
            gender =1;
        }
        UserUpdateDTO user = new UserUpdateDTO();
        user.setFullname(fullname);
        user.setGender(gender);
        user.setEmail(this.user.getUsername());

        user.setBirthDay(birthdate);

        initPresenter();
        presenter.updateMember(token,user);
//        String gender =
//        initPresenter();
//        presenter.updateMember(token,);
    }

    @Override
    public void updateMemberSuccess(User user) {
        edtEmail.setText(user.getEmail());
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        edtBirthdate.setText(sf.format(user.getDateOfBirth()));

        titleName.setText(user.getFullName());
        ApartTitleNo.setText(house.getHouseName());
        edtFullname.setFocusable(false);
        edtFullname.setEnabled(false);

        updateBtn.setVisibility(View.INVISIBLE);
        edtBtn.setVisibility(View.VISIBLE);
        updateBtn.setVisibility(View.INVISIBLE);

    }

    @Override
    public void updateMemberFail(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    private void showDate(String tag){
        DialogFragment fragment = new DateTimeFragment();
//        fragment.show(getSupportFragmentManager(),tag);
        fragment.show(getFragmentManager(),tag);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String day="",months="";

        if(dayOfMonth<10){
            day="0"+dayOfMonth;
        }else{
            day=dayOfMonth+"";
        }
        if (month+1<10){
            months="0"+(month+1);
        }else{
            months=month+1+"";
        }
        edtBirthdate.setText(year+"-"+months+"-"+day);


    }

    public void clickToSetDate(View view) {
        showDate("Birthdate");
    }
}
