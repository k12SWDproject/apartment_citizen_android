package com.example.projectswd.presenters;

import com.example.projectswd.contract.OrtherActivityContract;
import com.example.projectswd.model.HouseRecipt;
import com.example.projectswd.repositories.ReciptRepository;
import com.example.projectswd.repositories.ReciptRepositoryImp;
import com.example.projectswd.utils.CallBackData;

public class OrtherActivityPresenter implements OrtherActivityContract.presenter {

    private OrtherActivityContract.view view;
    private ReciptRepository reciptRepository;

    public OrtherActivityPresenter(OrtherActivityContract.view view) {
        this.view = view;
        reciptRepository = new ReciptRepositoryImp();
    }

    @Override
    public void getListOrtherReceipt(String token, String type) {
        reciptRepository.getListRecipt(token, type, new CallBackData<HouseRecipt>() {
            @Override
            public void success(HouseRecipt houseRecipt) {
                view.getListOrtherReceiptSuccess(houseRecipt);
            }

            @Override
            public void fail(String msg) {
                view.getListOrtherReceiptFail(msg);
            }
        });
    }
}
