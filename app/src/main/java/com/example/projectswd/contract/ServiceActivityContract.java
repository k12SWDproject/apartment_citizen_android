package com.example.projectswd.contract;

import com.example.projectswd.model.FilterName;
import com.example.projectswd.model.Product;

import java.util.List;

public interface ServiceActivityContract {
    interface presenter{
        void getListProduct(String token, FilterName filterName);
    }

    interface view{
        void getListProductSuccess(List<Product> products);
        void getListProductFail(String msg);
    }
}
