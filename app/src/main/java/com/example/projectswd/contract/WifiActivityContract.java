package com.example.projectswd.contract;

import com.example.projectswd.model.HouseRecipt;

public interface WifiActivityContract {
    interface presenter{
        void getListWifiReceipt(String token, String type);
    }

    interface view{
        void getListWifiReceiptSuccess(HouseRecipt houseRecipt);
        void getListWifiReceiptFail(String msg);
    }
}
