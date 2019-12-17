package com.hzq.dragonshopping.service;

import com.hzq.dragonshopping.entity.OrderEntity;

import java.util.List;

/**
 * @Auther: wdd
 * @Date: 2019/12/15 09:09
 * @Description:
 */
public interface IOrderService {
    List<Double> getPrice();

    List<Integer> getNum();

    void pay(Integer userId,String produceId, Integer orderNum, double totalPrice);

    List<OrderEntity> getList(Integer uid);

    List<OrderEntity> getListAll();

    void send(Integer id);
}
