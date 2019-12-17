package com.hzq.dragonshopping.service;

import com.hzq.dragonshopping.entity.ProducCommentsUserEntity;
import com.hzq.dragonshopping.entity.ProduceEntity;

import java.util.List;

public interface IProduceService {
    /**
     * 查询热门商品
     * @return
     */
    List<ProduceEntity> showHotCommody();
    /**
     * 查询商品详情和评论
     * @return
     */
    List<ProducCommentsUserEntity> selectProducDetailsAndComments(int producId);

    /**
     * 搜索商品
     * @return
     */
    List<ProduceEntity> searchProduce(ProduceEntity produceEntity);

    void add(ProduceEntity produceEntity);

    void del(int id);

    ProduceEntity selectById(int id);

    void update(ProduceEntity produceEntity);
}
