package com.example.projectswd.repositories;

import com.example.projectswd.model.HouseRecipt;
import com.example.projectswd.utils.CallBackData;
import com.example.projectswd.utils.ClientApi;

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
}
