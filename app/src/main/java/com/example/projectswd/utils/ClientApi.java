package com.example.projectswd.utils;

import com.example.projectswd.repositories.APIService;

public class ClientApi extends BaseApi {

    public APIService APIService(){
        return this.getService(APIService.class, ConfigAPI.baseUrl);
    }
}
