package com.example.projectswd.contract;

import com.example.projectswd.model.CartObject;
import com.example.projectswd.model.PayObject;

import java.util.List;

import okhttp3.ResponseBody;

public interface CartActivityContract {
    interface presenter{
        void payment(String token, List<PayObject> cartObject);
    }
    interface view{
        void paymentSuccess(ResponseBody responseBody);
        void paymentFail(String msg);
    }
}
