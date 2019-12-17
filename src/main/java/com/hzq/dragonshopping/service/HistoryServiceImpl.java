package com.hzq.dragonshopping.service;

import com.hzq.dragonshopping.entity.History;
import com.hzq.dragonshopping.entity.ProduceEntity;
import com.hzq.dragonshopping.mapper.HistoryMapper;
import com.hzq.dragonshopping.mapper.ProduceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Auther: wdd
 * @Date: 2019/12/15 12:08
 * @Description:
 */
@Service
public class HistoryServiceImpl implements IHistoryService{

    @Autowired
    private ProduceMapper produceMapper;
    @Autowired
    private HistoryMapper historyMapper;

    @Override
    public void add(Integer userId, int productId) {
        ProduceEntity produceEntity = produceMapper.selectById(productId);
        History history = new History();
        history.setPrice(produceEntity.getProduce_price());
        history.setTitle(produceEntity.getProduce_name());
        history.setUserId(userId);
        history.setCreateTime(new Date());
        historyMapper.add(history);
    }

    @Override
    public List<History> getAll() {
        return historyMapper.selectAll();
    }
}
