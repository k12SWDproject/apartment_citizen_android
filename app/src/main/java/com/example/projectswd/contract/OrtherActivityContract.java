package com.example.projectswd.contract;

import com.example.projectswd.model.HouseRecipt;

public interface OrtherActivityContract {
    interface presenter{
        void getListOrtherReceipt(String token, String type);
    }

    interface view{
        void getListOrtherReceiptSuccess(HouseRecipt houseRecipt);
        void getListOrtherReceiptFail(String msg);
    }
}
