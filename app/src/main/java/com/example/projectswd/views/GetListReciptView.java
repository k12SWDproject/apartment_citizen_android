package com.example.projectswd.views;

import com.example.projectswd.model.HouseRecipt;

public interface GetListReciptView {
    void getListReciptSuccess(HouseRecipt houseRecipt);
    void getListReciptFail(String msg);
}
