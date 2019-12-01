package com.example.projectswd.views;

import com.example.projectswd.model.HouseRecipt;
import com.example.projectswd.model.Receipt;
import com.example.projectswd.model.ReceiptDTO;

public interface DetailReceiptView {
    void success(ReceiptDTO receipt);
    void fail(String msg);

}
