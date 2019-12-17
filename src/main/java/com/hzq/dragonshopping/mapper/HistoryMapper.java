package com.hzq.dragonshopping.mapper;

import com.hzq.dragonshopping.entity.History;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Auther: wdd
 * @Date: 2019/12/15 12:12
 * @Description:
 */
@Mapper
public interface HistoryMapper {
    void add(History history);

    List<History> selectAll();
}
