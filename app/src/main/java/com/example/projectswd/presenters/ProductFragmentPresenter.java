package com.example.projectswd.presenters;

import com.example.projectswd.contract.ProductFragmentContract;
import com.example.projectswd.model.OrderDTO;
import com.example.projectswd.repositories.ProductRepository;
import com.example.projectswd.repositories.ProductRepositoryImp;
import com.example.projectswd.utils.CallBackData;

import java.util.List;

public class ProductFragmentPresenter implements ProductFragmentContract.presenter {
    private ProductFragmentContract.view view;
    private ProductRepository repository;

    public ProductFragmentPresenter(ProductFragmentContract.view view) {
        this.view = view;
        repository = new ProductRepositoryImp();
    }

    @Override
    public void getOrders(String token) {
        repository.getOrders(token, new CallBackData<List<OrderDTO>>() {
            @Override
            public void success(List<OrderDTO> orderDTOS) {
                view.getOrderSuccess(orderDTOS);
            }

            @Override
            public void fail(String msg) {
                view.getOrdersFail(msg);
            }
        });

    }
}
