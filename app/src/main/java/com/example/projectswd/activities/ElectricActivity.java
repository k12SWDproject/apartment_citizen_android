package com.example.projectswd.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.projectswd.R;
import com.example.projectswd.adapter.ElectricAdapter;
import com.example.projectswd.repositories.APIService;
import com.example.projectswd.model.HouseRecipt;
import com.example.projectswd.model.Receipts;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ElectricActivity extends AppCompatActivity {
    ListView lvListNotPayedReceipt,lvListPayedReceipt;
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electric);
        Intent intent = getIntent();
       token =  intent.getStringExtra("TOKEN");
        getListRecipts();

    }

    private void getListRecipts(){

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://spwproject.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build();
        APIService apiService = retrofit.create(APIService.class);
        Call<HouseRecipt> call = apiService.getListRecipts("WATER_TYPE", token);

        call.enqueue(new Callback<HouseRecipt>() {
            @Override
            public void onResponse(Call<HouseRecipt> call, Response<HouseRecipt> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(ElectricActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    return;
                }
                HouseRecipt houseRecipt = response.body();

                List<Receipts> listPayedReceipt = new ArrayList<>();
                    List<Receipts> listNotPayedReceipt = new ArrayList<>();

                listPayedReceipt = houseRecipt.getListPayedReceipt();
                listNotPayedReceipt = houseRecipt.getListNotPayedReceipt();

                lvListPayedReceipt  = findViewById(R.id.lvDienDaThanhToan);
                lvListNotPayedReceipt = findViewById(R.id.lvDienChuaThanhToan);
                ElectricAdapter adapter = new ElectricAdapter();
                ElectricAdapter adapter2 = new ElectricAdapter();
                adapter.setListReceipt(listPayedReceipt);
                lvListPayedReceipt.setAdapter(adapter);
                lvListPayedReceipt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                });
                adapter2.setListReceipt(listNotPayedReceipt);
                lvListNotPayedReceipt.setAdapter(adapter2);
                lvListNotPayedReceipt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                });
            }

            @Override
            public void onFailure(Call<HouseRecipt> call, Throwable t) {
                Toast.makeText(ElectricActivity.this, "Failed!!!!!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
