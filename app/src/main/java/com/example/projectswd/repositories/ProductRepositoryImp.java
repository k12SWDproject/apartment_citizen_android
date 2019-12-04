package com.example.projectswd.repositories;

import com.example.projectswd.model.FilterObj;
import com.example.projectswd.model.FilterProduct;
import com.example.projectswd.model.Product;
import com.example.projectswd.utils.CallBackData;
import com.example.projectswd.utils.ClientApi;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepositoryImp implements ProductRepository {

    @Override
    public void getListProduct(String token, FilterProduct filterObj, final CallBackData<List<Product>> callBackData) {

        Gson gson = new Gson();
        String json = gson.toJson(filterObj);
        ClientApi clientApi = new ClientApi();
        Call<List<Product>> call = clientApi.APIService().getListProduct(token,json);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                try {
                    if(response.code() ==200){
                        List<Product> list = response.body();
                        callBackData.success(list);
                    }else{
                        callBackData.fail("Failed !!");
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }
}
