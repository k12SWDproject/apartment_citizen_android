package com.example.projectswd.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectswd.R;
import com.example.projectswd.model.House;
import com.example.projectswd.model.User;

import java.text.SimpleDateFormat;

public class ProfileActivity extends AppCompatActivity {
    EditText edtFullname, setGender, edtEmail, edtPhoneNo, edtBirthdate;
    TextView titleName,ApartTitleNo;
    Button edtBtn, updateBtn, btnManage;
    House house;
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("USERINFO");
        token = intent.getStringExtra("TOKEN");
        house = user.getHouse();

        edtFullname = findViewById(R.id.edtFullname);
        setGender = findViewById(R.id.edtGender);
        edtEmail = findViewById(R.id.edtEmail);
        edtPhoneNo = findViewById(R.id.edtNumberphone);
        edtBirthdate = findViewById(R.id.edtBirthdate);
        titleName = findViewById(R.id.titleName);
        ApartTitleNo = findViewById(R.id.numberApartment);
        edtBtn = findViewById(R.id.editButton);
        updateBtn = findViewById(R.id.updateButton);
        btnManage = findViewById(R.id.btnManage);

        btnManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(),ManageMembersActivity.class);
                intent1.putExtra("TOKEN",token);
                startActivity(intent1);


            }
        });



        edtFullname.setText(user.getFullName());
        setGender.setText(user.getGender() == 1 ? "Nam" : "Nữ");
        edtEmail.setText("trinmse63349@fpt.edu.vn");
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        edtBirthdate.setText(sf.format(user.getDateOfBirth()));
        edtPhoneNo.setText(user.getIdNumber() + "");
        titleName.setText(user.getFullName());
        ApartTitleNo.setText(house.getHouseName());


    }


    public void clickToEdit(View view) {
        edtFullname.setEnabled(true);
        edtFullname.isFocusable();
        edtEmail.setEnabled(true);
        edtBirthdate.setEnabled(true);
        edtPhoneNo.setEnabled(true);
        setGender.setEnabled(true);
        updateBtn.setVisibility(View.VISIBLE);
        edtBtn.setVisibility(View.INVISIBLE);

    }
}
