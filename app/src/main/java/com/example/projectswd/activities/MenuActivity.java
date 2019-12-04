package com.example.projectswd.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.projectswd.fragments.HomeFragment;
import com.example.projectswd.R;
import com.example.projectswd.model.CartObject;
import com.example.projectswd.model.Product;
import com.example.projectswd.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {
    private TextView txtNameOwner,txtApartmentNumber;


    private User user;
    private  String token;

    public static List<CartObject> productList;
    public static List<Product> productsToCompare;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =new
            BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selctFrag = null;

                    switch (menuItem.getItemId()){
                        case R.id.navigation_dashboard:
//                            selctFrag = new ProductFragment();
                            break;
                        case R.id.navigation_home:
                            Bundle intent = new Bundle();
                            intent.putSerializable("USERINFO",user);
                            intent.putString("TOKEN",token);
                            selctFrag= new HomeFragment();
                            selctFrag.setArguments(intent);
                            break;
                        case R.id.navigation_notifications:
//                            selctFrag= new WarehouseFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, selctFrag).commit();
                    return true;
                }

            };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        productList = new ArrayList<>();
        productsToCompare = new ArrayList<>();
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("USERINFO");
        token = intent.getStringExtra("TOKEN");

        txtNameOwner = findViewById(R.id.txtNameOwner);
        txtApartmentNumber = findViewById(R.id.txtAparmentID);

        txtNameOwner.setText(user.getFullName());
        txtApartmentNumber.setText(user.getHouse().getHouseName());

        Bundle bundle = new Bundle();
        bundle.putSerializable("USERINFO",user);
        bundle.putString("TOKEN",token);
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(bundle);

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, homeFragment).commit();
    }

    public void clickToElectric(View view) {
        Intent  intent = new Intent(getApplicationContext(), ElectricActivity.class);
        intent.putExtra("TOKEN", token);
        intent.putExtra("USERINFO",user);
        startActivity(intent);
    }


    public void clickToWater(View view) {
        Intent  intent = new Intent(getApplicationContext(), WaterActivity.class);
        intent.putExtra("TOKEN", token);
        intent.putExtra("USERINFO",user);
        startActivity(intent);
    }

    public void clickToWifi(View view) {
        Intent  intent = new Intent(getApplicationContext(), WifiActivity.class);
        intent.putExtra("TOKEN", token);
        intent.putExtra("USERINFO",user);
        startActivity(intent);
    }

    public void clickToPhiChungCu(View view) {
        Intent  intent = new Intent(getApplicationContext(), OrtherActivity.class);
        intent.putExtra("TOKEN", token);
        intent.putExtra("USERINFO",user);
        startActivity(intent);
    }

    public void clickToProfile(View view) {
        Intent  intent = new Intent(getApplicationContext(), ProfileActivity.class);
        intent.putExtra("TOKEN", token);
        intent.putExtra("USERINFO",user);
        startActivity(intent);
    }

    public void clickToService(View view) {
        Intent  intent = new Intent(getApplicationContext(), ServiceActivity.class);
        intent.putExtra("TOKEN", token);
        intent.putExtra("USERINFO",user);
        startActivity(intent);
    }
}
