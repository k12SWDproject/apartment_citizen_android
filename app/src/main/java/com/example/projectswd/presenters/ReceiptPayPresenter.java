package com.example.projectswd.presenters;

import com.example.projectswd.repositories.ReciptRepository;
import com.example.projectswd.repositories.ReciptRepositoryImp;
import com.example.projectswd.utils.CallBackData;
import com.example.projectswd.views.ReceiptPayView;

import okhttp3.ResponseBody;

public class ReceiptPayPresenter {
    private ReceiptPayView receiptPayView;
    private ReciptRepository reciptRepository;

    public ReceiptPayPresenter(ReceiptPayView receiptPayView) {
        this.receiptPayView = receiptPayView;
        reciptRepository = new ReciptRepositoryImp();
    }
    public  void payReceipt(String token, int id){
        reciptRepository.payReceipta(token, id, new CallBackData<ResponseBody>() {
            @Override
            public void success(ResponseBody responseBody) {
                receiptPayView.success("Success");
            }

            @Override
            public void fail(String msg) {
                receiptPayView.success(msg);
            }
        });
    }
}
