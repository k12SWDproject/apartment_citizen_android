package com.example.projectswd.repositories;



import com.example.projectswd.model.HouseRecipt;
import com.example.projectswd.model.User;
import com.example.projectswd.utils.ConfigAPI;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {


    @POST(ConfigAPI.Api.LOGIN)
    Call<ResponseBody> gettToken(@Query("username") String username, @Query("password") String password);

    @GET(ConfigAPI.Api.GETUSER)
        Call<User> getInforByUsername (@Path("username") String username, @Header("Authorization")  String token );
    // @Header("Author") String
//    @GET(ConfigAPI.Api.GETRECEIPTS)
//    Call<HouseRecipt> getListRecipts (@Path("type") String type,@Header("Authorization")  String token );

    @GET(ConfigAPI.Api.GETRECEIPTS)
    @Headers("Content-Type:application/json")
    Call<HouseRecipt> getListRecipts (@Path("type") String type,@Header("Authorization")  String token );
}
