package com.example.projectswd.contract;

import com.example.projectswd.model.FilterObj;
import com.example.projectswd.model.ReceiptDTO;

public interface DetailActivityContract {

    interface presenter{
        void showDetail(String token, FilterObj filterObj);
        void payReceipt(String token, int id);
    }


    interface view{
        void showDetailSuccess(ReceiptDTO receipt);
        void showDetailFail(String msg);

        void payReceiptSuccess(String string);
        void payReceiptFail(String msg);
    }
}
