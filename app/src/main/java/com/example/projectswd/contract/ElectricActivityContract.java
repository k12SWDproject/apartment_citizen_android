package com.example.projectswd.contract;

import com.example.projectswd.model.HouseRecipt;

public interface ElectricActivityContract {
    interface presenter{
        void getListElectricReceipt(String token, String type);
    }

    interface view{
        void getListElectricReceiptSuccess(HouseRecipt houseRecipt);
        void getListElectricReceiptFail(String msg);
    }
}
