package com.example.projectswd.repositories;

import com.example.projectswd.model.FilterObj;
import com.example.projectswd.model.HouseRecipt;
import com.example.projectswd.model.Product;
import com.example.projectswd.model.Receipt;
import com.example.projectswd.model.ReceiptDTO;
import com.example.projectswd.model.User;
import com.example.projectswd.utils.CallBackData;
import com.example.projectswd.utils.ClientApi;
import com.google.gson.Gson;

import java.util.List;

import okhttp3.ResponseBody;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ReciptRepositoryImp implements ReciptRepository {



    @Override
    public void getListRecipt(String token, String typeRecipt, final CallBackData<HouseRecipt> callBackData) {

        ClientApi clientApi = new ClientApi();
        Call<HouseRecipt> call = clientApi.APIService().getListRecipts( typeRecipt,token);
        call.enqueue(new Callback<HouseRecipt>() {
            @Override
            public void onResponse(Call<HouseRecipt> call, Response<HouseRecipt> response) {
                try {
                    if (response.code() == 200) {

                        HouseRecipt houseRecipt = response.body();
                        if (houseRecipt != null) {
                            callBackData.success(houseRecipt);
                        }

                    } else {
                        callBackData.fail("Bạn không có hóa đơn nào");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<HouseRecipt> call, Throwable t) {

            }
        });

    }

    @Override
    public void getDetailReceipt(String token, FilterObj filterObj,final CallBackData<ReceiptDTO> callBackData) {

        Gson gson = new Gson();
        String json = gson.toJson(filterObj);

        ClientApi clientApi = new ClientApi();
        Call<ReceiptDTO> call = clientApi.APIService().getDetailRecipt( token, json);
        call.enqueue(new Callback<ReceiptDTO>() {
            @Override
            public void onResponse(Call<ReceiptDTO> call, Response<ReceiptDTO> response) {
                try {
                    if (response.code() == 200) {

                        ReceiptDTO receipt = response.body();
                        if (receipt != null) {
                            callBackData.success(receipt);
                        }

                    } else {
                        callBackData.fail("Lấy thông tin thất bại");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ReceiptDTO> call, Throwable t) {

            }
        });
//

    }

    @Override
    public void payReceipta(String token, int id, final CallBackData<ResponseBody> callBackData) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> call = clientApi.APIService().payReceipt(token, id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if(response.code() ==200){
                        callBackData.success(response.body());
                    }else{
                        callBackData.fail("Thanh toán thất bại");
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }




}
