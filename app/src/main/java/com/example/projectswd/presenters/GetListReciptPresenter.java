package com.example.projectswd.presenters;

import com.example.projectswd.model.HouseRecipt;
import com.example.projectswd.repositories.ReciptRepository;
import com.example.projectswd.repositories.ReciptRepositoryImp;
import com.example.projectswd.utils.CallBackData;
import com.example.projectswd.views.GetListReciptView;

public class GetListReciptPresenter {
    private GetListReciptView listReciptView;
    private ReciptRepository reciptRepository;


    public GetListReciptPresenter(GetListReciptView listReciptView) {

        this.listReciptView  = listReciptView;
        reciptRepository = new ReciptRepositoryImp();
    }

    public void getList(String token, String type){
        reciptRepository.getListRecipt(token, type, new CallBackData<HouseRecipt>() {
            @Override
            public void success(HouseRecipt houseRecipt) {
                listReciptView.getListReciptSuccess(houseRecipt);
            }

            @Override
            public void fail(String msg) {
                listReciptView.getListReciptFail(msg);
            }
        });

    }
}
