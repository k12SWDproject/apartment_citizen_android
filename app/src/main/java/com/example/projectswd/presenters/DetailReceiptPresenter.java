package com.example.projectswd.presenters;

import com.example.projectswd.model.FilterObj;

import com.example.projectswd.model.ReceiptDTO;
import com.example.projectswd.repositories.ReciptRepository;
import com.example.projectswd.repositories.ReciptRepositoryImp;
import com.example.projectswd.utils.CallBackData;
import com.example.projectswd.views.DetailReceiptView;


public class DetailReceiptPresenter {
    private DetailReceiptView detailReceiptView;

    private ReciptRepository reciptRepository;

    public DetailReceiptPresenter(DetailReceiptView detailReceiptView) {
        this.detailReceiptView = detailReceiptView;
       reciptRepository = new ReciptRepositoryImp();

    }

    public void getDetailReceipt(String token, FilterObj filterObj){
        reciptRepository.getDetailReceipt(token, filterObj, new CallBackData<ReceiptDTO>() {
            @Override
            public void success(ReceiptDTO receiptDTO) {
                detailReceiptView.success(receiptDTO);
            }

            @Override
            public void fail(String msg) {
                detailReceiptView.fail(msg);
            }
        });

    }


}
