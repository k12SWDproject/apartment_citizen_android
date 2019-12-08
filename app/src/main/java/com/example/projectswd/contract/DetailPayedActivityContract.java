package com.example.projectswd.contract;

import com.example.projectswd.model.FilterObj;
import com.example.projectswd.model.ReceiptDTO;

public interface DetailPayedActivityContract {
    interface presenter{
        void showDetailPayed(String token, FilterObj filterObj);

    }


    interface view{
        void showDetailSuccessPayed(ReceiptDTO receipt);
        void showDetailFailPayed(String msg);


    }
}
