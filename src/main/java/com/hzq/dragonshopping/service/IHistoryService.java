package com.hzq.dragonshopping.service;

import com.hzq.dragonshopping.entity.History;

import java.util.List;

/**
 * @Auther: wdd
 * @Date: 2019/12/15 12:08
 * @Description:
 */
public interface IHistoryService {
    void add(Integer userId, int productId);

    List<History> getAll();

}
