package com.example.projectswd.repositories;

import com.example.projectswd.model.FilterName;
import com.example.projectswd.model.OrderDTO;
import com.example.projectswd.model.PayObject;
import com.example.projectswd.model.Product;
import com.example.projectswd.utils.CallBackData;
import com.example.projectswd.utils.ClientApi;
import com.google.gson.Gson;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepositoryImp implements ProductRepository {

    @Override
    public void getListProduct(String token, FilterName filterObj, final CallBackData<List<Product>> callBackData) {

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

    @Override
    public void payForOrderProduct(String token, List<PayObject> payObject, final CallBackData<ResponseBody> callBackData) {
        Gson gson = new Gson();
        String json = gson.toJson(payObject);

        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> call = clientApi.APIService().paymentProducts(token,payObject);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if(response.code() ==200){
                        callBackData.success(response.body());
                    }else{
                        callBackData.fail("Thanh Toán Không Thành Công");
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callBackData.fail("Thanh Toán Không Thành Công");
            }
        });


    }

    @Override
    public void getOrders(String token, final CallBackData<List<OrderDTO>> callBackData) {
        ClientApi clientApi = new ClientApi();
        Call<List<OrderDTO>> call = clientApi.APIService().getOrders(token);
        call.enqueue(new Callback<List<OrderDTO>>() {
            @Override
            public void onResponse(Call<List<OrderDTO>> call, Response<List<OrderDTO>> response) {
                try{
                    if(response.code() == 200){
                        List<OrderDTO> dtos = response.body();
                        if (dtos.size()>0){
                            callBackData.success(dtos);
                        }else{
                            callBackData.fail("Bạn không có đơn đặt hàng nào");
                        }
                    }else{
                        callBackData.fail("Vui lòng kiểm tra lại");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<OrderDTO>> call, Throwable t) {
                callBackData.fail("Vui lòng kiểm tra lại");
            }
        });
    }


}
