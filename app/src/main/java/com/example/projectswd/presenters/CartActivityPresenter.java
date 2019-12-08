package com.example.projectswd.presenters;

import com.example.projectswd.contract.CartActivityContract;
import com.example.projectswd.model.CartObject;
import com.example.projectswd.model.PayObject;
import com.example.projectswd.repositories.ProductRepository;
import com.example.projectswd.repositories.ProductRepositoryImp;
import com.example.projectswd.utils.CallBackData;
import com.example.projectswd.utils.ClientApi;
import com.google.gson.Gson;

import java.util.List;

import okhttp3.ResponseBody;

public class CartActivityPresenter implements CartActivityContract.presenter {

    private CartActivityContract.view view;
    private ProductRepository productRepository;

    public CartActivityPresenter(CartActivityContract.view view) {
        this.view = view;
        productRepository = new ProductRepositoryImp();
    }

    @Override
    public void payment(String token, List<PayObject> cartObject) {



       productRepository.payForOrderProduct(token, cartObject, new CallBackData<ResponseBody>() {
           @Override
           public void success(ResponseBody responseBody) {

               view.paymentSuccess(responseBody);
           }

           @Override
           public void fail(String msg) {
                view.paymentFail(msg);
           }
       });


    }


}
