package com.example.projectswd.fragments;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectswd.R;
import com.example.projectswd.activities.ManageMembersActivity;
import com.example.projectswd.activities.MenuActivity;
import com.example.projectswd.contract.ProfileActivityContract;
import com.example.projectswd.model.House;
import com.example.projectswd.model.User;
import com.example.projectswd.model.UserUpdateDTO;
import com.example.projectswd.presenters.ProfileActivityPresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends DialogFragment implements ProfileActivityContract.view, DatePickerDialog.OnDateSetListener {




    public ProfileFragment() {
        // Required empty public constructor
    }

    EditText edtFullname, edtEmail, edtPhoneNo;
    public EditText edtBirthdate;
    TextView titleName;
    Button edtBtn, updateBtn, btnManage, btnCalender;
    Spinner spinnerGender;
    private House house;
    private String token;
    private User user;
    private ProfileActivityPresenter presenter;
    private ArrayAdapter<String> dataAdapter;
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_profile, container, false);
        // Inflate the layout for this fragment
        final Intent intent = getActivity().getIntent();
        user = (User) intent.getSerializableExtra("USERINFO");
        token = intent.getStringExtra("TOKEN");
        house = user.getHouse();





        edtFullname = view.findViewById(R.id.edtFullname);
        spinnerGender = view.findViewById(R.id.spnGender);
        edtEmail = view.findViewById(R.id.edtEmail);
        edtPhoneNo = view.findViewById(R.id.edtNumberphone);
        edtBirthdate = view.findViewById(R.id.edtBirthdate);
        edtBtn = view.findViewById(R.id.editButton);
        updateBtn = view.findViewById(R.id.updateButton);
        btnManage = view.findViewById(R.id.btnManage);
        btnCalender = view.findViewById(R.id.btnCalenderBirth);


        List<String> gender = new ArrayList<>();

        gender.add("Nam");
        gender.add("Nữ");
       dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, gender);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(dataAdapter);


//        initPresenter();
//        presenter.getUser(token,user.getUsername());

        btnManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity(), ManageMembersActivity.class);
                intent1.putExtra("USERINFO", user);
                intent1.putExtra("TOKEN", token);
                startActivity(intent1);


            }
        });

        if(user.getFullName() == null){
            edtFullname.setText(user.getUsername());
        }else{
            edtFullname.setText(user.getFullName());
        }


        if(user.getGender()==null){
            spinnerGender.setSelection(dataAdapter.getPosition("Nam"));
        }else{
            if (user.getGender() == 1) {
//            spinnerGender.setAdapter();
                spinnerGender.setSelection(dataAdapter.getPosition("Nam"));


            } else {
                spinnerGender.setSelection(dataAdapter.getPosition("Nữ"));

            }
        }


        edtEmail.setText(user.getUsername());
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        if(user.getDateOfBirth()==null){
            edtBirthdate.setText(sf.format(date));
        }else{
            edtBirthdate.setText(sf.format(user.getDateOfBirth()));
        }




        edtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtFullname.setFocusable(true);
                edtFullname.setEnabled(true);

                updateBtn.setVisibility(View.VISIBLE);
                edtBtn.setVisibility(View.INVISIBLE);
            }
        });


        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname = edtFullname.getText().toString();
//

                String birthdate = edtBirthdate.getText().toString();
                int gender = 0;
                if (spinnerGender.getSelectedItem() == "Nam") {
                    gender = 1;
                }
                UserUpdateDTO userDTO = new UserUpdateDTO();
                userDTO.setFullname(fullname);
                userDTO.setGender(gender);
                userDTO.setEmail(user.getUsername());

                userDTO.setBirthDay(birthdate);

                initPresenter();
                presenter.updateMember(token, userDTO);


            }
        });



       this.showDatePickerDialog();


        return view;
    }

    private void initPresenter() {
        presenter = new ProfileActivityPresenter(this);
    }



    @Override
    public void updateMemberSuccess(User user) {

        initPresenter();
        presenter.getUser(token,user.getUsername());

    }

    @Override
    public void updateMemberFail(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getUserSuccess(User user) {

        edtFullname.setText(user.getFullName());
        if (user.getGender() == 1) {
//            spinnerGender.setAdapter();
            spinnerGender.setSelection(dataAdapter.getPosition("Nam"));


        } else {
            spinnerGender.setSelection(dataAdapter.getPosition("Nữ"));

        }
        edtFullname.setEnabled(false);
        edtEmail.setText(user.getUsername());
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        edtBirthdate.setText(sf.format(user.getDateOfBirth()));
        edtBtn.setVisibility(View.VISIBLE);
        updateBtn.setVisibility(View.INVISIBLE);
    }

    @Override
    public void getUserFail(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//        setDate(String.valueOf(new StringBuilder().append(date).append("/").append(month).append("/").append(year).append("")));
//    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String day = "", months = "";

        if (dayOfMonth < 10) {
            day = "0" + dayOfMonth;
        } else {
            day = dayOfMonth + "";
        }
        if (month + 1 < 10) {
            months = "0" + (month + 1);
        } else {
            months = month + 1 + "";
        }
        showDate(year,months,day);


    }
//
    private void showDate(int year, String months, String day){
        edtBirthdate.setText(year + "-" + months + "-" + day);
    }

    private void showDatePickerDialog()
    {
        // Get open DatePickerDialog button.
//        Button datePickerDialogButton = btnCalender(R.id.datePickerDialogButton);
        btnCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new OnDateSetListener instance. This listener will be invoked when user click ok button in DatePickerDialog.
                DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        StringBuffer strBuf = new StringBuffer();

                        strBuf.append(year);
                        strBuf.append("-");
                        strBuf.append(month+1);
                        strBuf.append("-");
                        strBuf.append(dayOfMonth);


                        edtBirthdate.setText(strBuf.toString());
                    }
                };

                // Get current year, month and day.
                Calendar now = Calendar.getInstance();
                int year = now.get(java.util.Calendar.YEAR);
                int month = now.get(java.util.Calendar.MONTH);
                int day = now.get(java.util.Calendar.DAY_OF_MONTH);

                // Create the new DatePickerDialog instance.
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), onDateSetListener, year, month, day);

                // Set dialog icon and title.
//                datePickerDialog.setIcon(R.drawable.if_snowman);


                // Popup the dialog.
                datePickerDialog.show();
            }
        });
    }
}
