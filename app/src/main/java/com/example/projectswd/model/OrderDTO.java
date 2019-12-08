package com.example.projectswd.model;

import java.io.Serializable;
import java.util.List;

public class OrderDTO implements Serializable {
    private Order order;
    private List<OrderDetail> orderDetail;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderDetail> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetail> orderDetail) {
        this.orderDetail = orderDetail;
    }
}
