package com.example.projectswd.apiservice;



import com.example.projectswd.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface APIService {

    @GET("")
    Call<User> getInforByUsername(@Body String username);
}
