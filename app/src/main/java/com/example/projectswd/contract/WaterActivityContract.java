package com.example.projectswd.contract;

import com.example.projectswd.model.HouseRecipt;

public interface WaterActivityContract {
    interface presenter{
        void getListWaterReceipt(String token, String type);
    }

    interface view{
        void getListWaterReceiptSuccess(HouseRecipt houseRecipt);
        void getListWaterReceiptFail(String msg);
    }
}
