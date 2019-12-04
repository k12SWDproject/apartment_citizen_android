package com.example.projectswd.presenters;

import com.example.projectswd.contract.ElectricActivityContract;
import com.example.projectswd.model.HouseRecipt;
import com.example.projectswd.repositories.ReciptRepository;
import com.example.projectswd.repositories.ReciptRepositoryImp;
import com.example.projectswd.utils.CallBackData;

public class ElectricActivityPresenter implements ElectricActivityContract.presenter {

    private ElectricActivityContract.view view;

    public ElectricActivityPresenter(ElectricActivityContract.view view) {
        this.view = view;
        reciptRepository = new ReciptRepositoryImp();
    }
    private ReciptRepository reciptRepository;

    @Override
    public void getListElectricReceipt(String token, String type) {
        reciptRepository.getListRecipt(token, type, new CallBackData<HouseRecipt>() {
            @Override
            public void success(HouseRecipt houseRecipt) {
                view.getListElectricReceiptSuccess(houseRecipt);
            }

            @Override
            public void fail(String msg) {
                view.getListElectricReceiptFail(msg);
            }
        });
    }
}
