package com.example.projectswd.presenters;

import com.example.projectswd.contract.DetailActivityContract;
import com.example.projectswd.model.FilterObj;
import com.example.projectswd.model.ReceiptDTO;
import com.example.projectswd.repositories.ReciptRepository;
import com.example.projectswd.repositories.ReciptRepositoryImp;
import com.example.projectswd.utils.CallBackData;
import com.google.gson.Gson;

import okhttp3.ResponseBody;

public class DetailActivityPresenter implements DetailActivityContract.presenter {
    private DetailActivityContract.view view;
    private ReciptRepository reciptRepository;

    public DetailActivityPresenter(DetailActivityContract.view view) {
        this.view = view;
        reciptRepository = new ReciptRepositoryImp();
    }

    @Override
    public void showDetail(String token, FilterObj filterObj) {

        Gson gson = new Gson();
        gson.toJson(filterObj);
        reciptRepository.getDetailReceipt(token, filterObj, new CallBackData<ReceiptDTO>() {
            @Override
            public void success(ReceiptDTO receiptDTO) {
                view.showDetailSuccess(receiptDTO);
            }

            @Override
            public void fail(String msg) {
                view.showDetailFail(msg);
            }
        });
    }

    @Override
    public void payReceipt(String token, int id) {
        reciptRepository.payReceipta(token, id, new CallBackData<ResponseBody>() {
            @Override
            public void success(ResponseBody responseBody) {
                view.payReceiptSuccess("Thanh Toán Thành Công");
            }

            @Override
            public void fail(String msg) {
                view.payReceiptFail(msg);
            }
        });
    }
}
