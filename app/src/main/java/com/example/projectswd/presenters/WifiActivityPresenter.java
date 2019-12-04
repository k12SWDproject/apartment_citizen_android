package com.example.projectswd.presenters;

import com.example.projectswd.contract.WifiActivityContract;
import com.example.projectswd.model.HouseRecipt;
import com.example.projectswd.repositories.ReciptRepository;
import com.example.projectswd.repositories.ReciptRepositoryImp;
import com.example.projectswd.utils.CallBackData;

public class WifiActivityPresenter implements WifiActivityContract.presenter {

    private WifiActivityContract.view view;
    private ReciptRepository reciptRepository;

    public WifiActivityPresenter(WifiActivityContract.view view) {
        this.view = view;
        reciptRepository = new ReciptRepositoryImp();
    }

    @Override
    public void getListWifiReceipt(String token, String type) {
        reciptRepository.getListRecipt(token, type, new CallBackData<HouseRecipt>() {
            @Override
            public void success(HouseRecipt houseRecipt) {
                view.getListWifiReceiptSuccess(houseRecipt);
            }

            @Override
            public void fail(String msg) {
                view.getListWifiReceiptFail(msg);
            }
        });
    }
}
