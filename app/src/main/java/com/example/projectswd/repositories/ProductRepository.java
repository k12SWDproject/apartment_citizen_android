package com.example.projectswd.repositories;

import com.example.projectswd.model.FilterName;
import com.example.projectswd.model.OrderDTO;
import com.example.projectswd.model.PayObject;
import com.example.projectswd.model.Product;
import com.example.projectswd.utils.CallBackData;

import java.util.List;

import okhttp3.ResponseBody;

public interface ProductRepository {
    void getListProduct (String token , FilterName filterName, CallBackData<List<Product>> callBackData);
    void payForOrderProduct(String token , List<PayObject> payObject, CallBackData<ResponseBody> callBackData);
    void getOrders(String token, CallBackData<List<OrderDTO>> callBackData);
}
