package com.example.projectswd.presenters;

import com.example.projectswd.contract.DetailPayedActivityContract;
import com.example.projectswd.model.FilterObj;
import com.example.projectswd.model.ReceiptDTO;
import com.example.projectswd.repositories.ReciptRepository;
import com.example.projectswd.repositories.ReciptRepositoryImp;
import com.example.projectswd.utils.CallBackData;
import com.google.gson.Gson;

public class DetailPayedActivityPresenter implements DetailPayedActivityContract.presenter {
    private DetailPayedActivityContract.view view;
    private ReciptRepository reciptRepository;

    public DetailPayedActivityPresenter(DetailPayedActivityContract.view view) {
        this.view = view;
        reciptRepository= new ReciptRepositoryImp();
    }

    @Override
    public void showDetailPayed(String token, FilterObj filterObj) {
        Gson gson = new Gson();
        gson.toJson(filterObj);
        reciptRepository.getDetailReceipt(token, filterObj, new CallBackData<ReceiptDTO>() {
            @Override
            public void success(ReceiptDTO receiptDTO) {
                view.showDetailSuccessPayed(receiptDTO);
            }

            @Override
            public void fail(String msg) {
                view.showDetailFailPayed(msg);
            }
        });

    }
}
