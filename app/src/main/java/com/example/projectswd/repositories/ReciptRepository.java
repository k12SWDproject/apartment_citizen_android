package com.example.projectswd.repositories;

import com.example.projectswd.model.HouseRecipt;
import com.example.projectswd.utils.CallBackData;

public interface ReciptRepository {

    void getListRecipt(String token, String typeRecipt, CallBackData<HouseRecipt> callBackData);
}
