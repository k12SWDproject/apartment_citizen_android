package com.example.projectswd.repositories;

import com.example.projectswd.model.FilterObj;
import com.example.projectswd.model.HouseRecipt;
import com.example.projectswd.model.Product;
import com.example.projectswd.model.Receipt;
import com.example.projectswd.model.ReceiptDTO;
import com.example.projectswd.utils.CallBackData;

import java.util.List;

import okhttp3.ResponseBody;

public interface ReciptRepository {

    void getListRecipt(String token, String typeRecipt, CallBackData<HouseRecipt> callBackData);
    void getDetailReceipt(String token , FilterObj filterObj, CallBackData<ReceiptDTO> callBackData);
    void payReceipta(String token,int id, CallBackData<ResponseBody> callBackData);

}
