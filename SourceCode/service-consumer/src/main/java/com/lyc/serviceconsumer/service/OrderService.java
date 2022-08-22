package com.lyc.serviceconsumer.service;


import com.lyc.serviceconsumer.entity.Order;

public interface OrderService {
    /**
     * 根据主键查询订单
     *
     * @param id
     * @return
     */
    Order selectOrderById(Integer id);

}
