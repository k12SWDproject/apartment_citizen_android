package com.example.projectswd.repositories;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.projectswd.R;
import com.example.projectswd.activities.ElectricActivity;
import com.example.projectswd.adapter.ElectricAdapter;
import com.example.projectswd.model.HouseRecipt;
import com.example.projectswd.model.Receipts;
import com.example.projectswd.utils.ConfigAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetListRecipts {
    public void getListReciptsElectric(String token){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ConfigAPI.baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        APIService apiService = retrofit.create(APIService.class);
        Call<HouseRecipt> call = apiService.getListRecipts("WATER_TYPE", token);

//        call.enqueue(new Callback<HouseRecipt>() {
//            @Override
//            public void onResponse(Call<HouseRecipt> call, Response<HouseRecipt> response) {
//                if (!response.isSuccessful()) {
//                    Toast.makeText(ElectricActivity.this, "Error", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                HouseRecipt houseRecipt = response.body();
//
//            }
//
//            @Override
//            public void onFailure(Call<HouseRecipt> call, Throwable t) {
//                Toast.makeText(ElectricActivity.this, "Failed!!!!!", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
