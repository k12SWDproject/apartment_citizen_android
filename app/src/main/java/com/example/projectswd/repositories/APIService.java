package com.example.projectswd.repositories;



import com.example.projectswd.model.FilterObj;
import com.example.projectswd.model.HouseRecipt;
import com.example.projectswd.model.OrderDTO;
import com.example.projectswd.model.PayObject;
import com.example.projectswd.model.Product;
import com.example.projectswd.model.Receipt;
import com.example.projectswd.model.ReceiptDTO;
import com.example.projectswd.model.User;
import com.example.projectswd.model.UserUpdateDTO;
import com.example.projectswd.utils.CallBackData;
import com.example.projectswd.utils.ConfigAPI;
import com.google.gson.Gson;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {


    @POST(ConfigAPI.Api.LOGIN)
    Call<ResponseBody> gettToken(@Query("username") String username, @Query("password") String password);

    @GET(ConfigAPI.Api.GETUSER)
    Call<User> getInforByUsername(@Path("username") String username, @Header("Authorization") String token);
    // @Header("Author") String
//    @GET(ConfigAPI.Api.GETRECEIPTS)
//    Call<HouseRecipt> getListRecipts (@Path("type") String type,@Header("Authorization")  String token );

    @GET(ConfigAPI.Api.GETRECEIPTS)
    @Headers("Content-Type:application/json")
    Call<HouseRecipt> getListRecipts(@Path("type") String type, @Header("Authorization") String token);

    @GET(ConfigAPI.Api.GETDETAILRECEIPT)
    @Headers("Content-Type:application/json")
    Call<ReceiptDTO> getDetailRecipt(@Header("Authorization") String token, @Query("filter") String json);

    @PUT(ConfigAPI.Api.PAYRECEIPT)
    @Headers("Content-Type:application/json")
    Call<ResponseBody> payReceipt(@Header("Authorization") String token, @Path("id") int id);

    @GET(ConfigAPI.Api.GETMEMBERS)
    @Headers("Content-Type:application/json")
    Call<List<User>> getMembers (@Header("Authorization")  String token, @Query("filter") String filter );

    @GET(ConfigAPI.Api.GETMEMBERS)
    @Headers("Content-Type:application/json")
    Call<List<User>> getMemberByEmail (@Header("Authorization")  String token, @Query("filter") String filter );

    @GET(ConfigAPI.Api.GETPRODUCTS)
    @Headers("Content-Type:application/json")
    Call<List<Product>> getListProduct(@Header("Authorization") String token, @Query("filter") String json);

    @POST(ConfigAPI.Api.PAYMENT)
    @Headers("Content-Type:application/json")
    Call<ResponseBody> paymentProducts(@Header("Authorization") String token, @Body List<PayObject> payObjects);

    @PUT(ConfigAPI.Api.UPDATELISTMEMER)
    @Headers("Content-Type:application/json")
    Call<ResponseBody> addMember (@Header("Authorization") String token, @Path("username") String username);

    @DELETE(ConfigAPI.Api.UPDATELISTMEMER)
    @Headers("Content-Type:application/json")
    Call<ResponseBody> deleteMember (@Header("Authorization") String token, @Path("username") String username);

    @PUT(ConfigAPI.Api.GETMEMBERS)
    @Headers("Content-Type:application/json")
    Call<User> updateMember (@Header("Authorization") String token, @Body UserUpdateDTO user);

    @GET(ConfigAPI.Api.GETORDERS)
    @Headers("Content-Type:application/json")
    Call<List<OrderDTO>> getOrders (@Header("Authorization") String token);
}