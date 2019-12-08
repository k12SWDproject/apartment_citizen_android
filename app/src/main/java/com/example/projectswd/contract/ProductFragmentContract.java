package com.example.projectswd.contract;

import com.example.projectswd.model.OrderDTO;

import java.util.List;

public interface ProductFragmentContract {
    interface presenter{
        void getOrders(String token);
    }
    interface view{
        void getOrderSuccess(List<OrderDTO> orderDTOS);
        void getOrdersFail(String msg);

    }
}
