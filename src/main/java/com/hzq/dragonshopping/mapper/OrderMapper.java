package com.hzq.dragonshopping.mapper;

import com.hzq.dragonshopping.entity.OrderEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Auther: wdd
 * @Date: 2019/12/15 09:12
 * @Description:
 */
@Mapper
public interface OrderMapper {
    Double findTotalPriceByDay(int i);

    Integer findTotalNumByDay(int i);

    void insert(OrderEntity order);

    List<OrderEntity> selectAll(Integer userid);

    List<OrderEntity> selectTotal();

    void updateState(Integer id);

    OrderEntity selectById(Integer id);
}
