package com.example.projectswd.apiservice;



import com.example.projectswd.model.User;

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


    @POST("login")
    Call<ResponseBody> gettToken(@Query("username") String username, @Query("password") String password);

    @GET("api/user/{username}")
    Call<User> getInforByUsername (@Path("username") String username, @Header("Authorization")  String token );
    // @Header("Author") String
}
