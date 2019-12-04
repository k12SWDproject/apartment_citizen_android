package com.example.projectswd.presenters;

import com.example.projectswd.contract.WaterActivityContract;
import com.example.projectswd.model.HouseRecipt;
import com.example.projectswd.repositories.ReciptRepository;
import com.example.projectswd.repositories.ReciptRepositoryImp;
import com.example.projectswd.utils.CallBackData;

public class WaterActivityPresenter implements WaterActivityContract.presenter {

    WaterActivityContract.view view;
    ReciptRepository reciptRepository;

    public WaterActivityPresenter(WaterActivityContract.view view) {
        this.view = view;
        reciptRepository = new ReciptRepositoryImp();
    }

    @Override
    public void getListWaterReceipt(String token, String type) {
        reciptRepository.getListRecipt(token, type, new CallBackData<HouseRecipt>() {
            @Override
            public void success(HouseRecipt houseRecipt) {
                view.getListWaterReceiptSuccess(houseRecipt);
            }

            @Override
            public void fail(String msg) {
                view.getListWaterReceiptFail(msg);
            }
        });
    }
}
