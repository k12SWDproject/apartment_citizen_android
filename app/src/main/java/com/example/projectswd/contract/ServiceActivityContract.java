package com.example.projectswd.contract;

import com.example.projectswd.model.FilterObj;
import com.example.projectswd.model.FilterProduct;
import com.example.projectswd.model.Product;

import java.util.List;

public interface ServiceActivityContract {
    interface presenter{
        void getListProduct(String token, FilterProduct filterProduct);
    }

    interface view{
        void getListProductSuccess(List<Product> products);
        void getListProductFail(String msg);
    }
}
