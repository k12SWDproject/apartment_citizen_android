package com.example.projectswd.repositories;

import com.example.projectswd.model.FilterObj;
import com.example.projectswd.model.FilterProduct;
import com.example.projectswd.model.Product;
import com.example.projectswd.utils.CallBackData;

import java.util.List;

public interface ProductRepository {
    void getListProduct (String token , FilterProduct filterProduct, CallBackData<List<Product>> callBackData);
}
