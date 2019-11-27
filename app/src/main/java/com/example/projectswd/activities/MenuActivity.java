package com.example.projectswd.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.projectswd.fragments.HomeFragment;
import com.example.projectswd.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuActivity extends AppCompatActivity {
    private TextView mTextMessage;
    private BottomNavigationView btna;
    private Fragment fragment;
    Intent intent = new Intent();
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
//                            selctFrag= new HomeFragment();
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

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, new HomeFragment()).commit();
    }
}
