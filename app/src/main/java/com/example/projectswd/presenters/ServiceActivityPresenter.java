package com.example.projectswd.presenters;

import com.example.projectswd.contract.ServiceActivityContract;
import com.example.projectswd.model.FilterObj;
import com.example.projectswd.model.FilterProduct;
import com.example.projectswd.model.Product;
import com.example.projectswd.repositories.ProductRepository;
import com.example.projectswd.repositories.ProductRepositoryImp;
import com.example.projectswd.utils.CallBackData;

import java.util.List;

public class ServiceActivityPresenter implements ServiceActivityContract.presenter {

    private ServiceActivityContract.view view;
    private ProductRepository productRepository;

    public ServiceActivityPresenter(ServiceActivityContract.view view) {
        this.view = view;
        productRepository = new ProductRepositoryImp();
    }

    @Override
    public void getListProduct(String token, FilterProduct filterProduct) {
        productRepository.getListProduct(token, filterProduct, new CallBackData<List<Product>>() {
            @Override
            public void success(List<Product> products) {
                view.getListProductSuccess(products);
            }

            @Override
            public void fail(String msg) {
                view.getListProductFail(msg);
            }
        });
    }
}
