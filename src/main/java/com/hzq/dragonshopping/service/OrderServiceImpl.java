package com.hzq.dragonshopping.service;

import com.hzq.dragonshopping.entity.OrderEntity;
import com.hzq.dragonshopping.entity.ProduceEntity;
import com.hzq.dragonshopping.entity.UserEntity;
import com.hzq.dragonshopping.mapper.OrderMapper;
import com.hzq.dragonshopping.mapper.ProduceMapper;
import com.hzq.dragonshopping.mapper.ShoppingCartMapper;
import com.hzq.dragonshopping.mapper.UserMapper;
import com.hzq.dragonshopping.untils.MailUtils;
import com.hzq.dragonshopping.untils.SendmailUtil2;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: wdd
 * @Date: 2019/12/15 09:09
 * @Description:
 */
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ProduceMapper produceMapper;
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Double> getPrice() {
        List<Double> prices = new ArrayList<>();
        for(int i=6; i>=0; i--){
            Double price = orderMapper.findTotalPriceByDay(i);
            if(price == null){
                prices.add((double) 0);
            }else{
                prices.add(orderMapper.findTotalPriceByDay(i));
            }
        }
        return prices;
    }

    @Override
    public List<Integer> getNum() {
        List<Integer> nums = new ArrayList<>();
        for(int i=6; i>=0; i--){
            Integer num = orderMapper.findTotalNumByDay(i);
            if(num == null){
                nums.add(0);
            }else{
                nums.add(orderMapper.findTotalNumByDay(i));
            }
        }
        return nums;
    }

    @Override
    public void pay(Integer userId,String produceId, Integer orderNum, double totalPrice) {
        OrderEntity order = new OrderEntity();
        order.setOrderNum(orderNum);
        order.setPayState(1);
        order.setPayTime(new Date());
        order.setTotalPrice(totalPrice);
        order.setUserId(userId);
        String[] split = produceId.split(",");
        for(String pid : split){
            if(!StringUtils.isEmpty(pid)){
                order.setProduceId(pid);
                orderMapper.insert(order);
                shoppingCartMapper.delete(userId,pid);
            }
        }
    }

    @Override
    public List<OrderEntity> getList(Integer uid) {
        List<OrderEntity> list = orderMapper.selectAll(uid);
        for(OrderEntity order : list ){
            String produceId = order.getProduceId();
            String[] split = produceId.split(",");
            String name = "";
            for(String s : split){
                if(!StringUtils.isEmpty(s)){
                    ProduceEntity produceEntity = produceMapper.selectById(new Integer(s));
                    name += produceEntity.getProduce_name() + ",";
                }
            }
            order.setProducName(name);
        }
        return list;
    }

    @Override
    public List<OrderEntity> getListAll() {
        List<OrderEntity> list = orderMapper.selectTotal();
//        for(OrderEntity order : list ){
//            String produceId = order.getProduceId();
//            String[] split = produceId.split(",");
//            String name = "";
//            for(String s : split){
//                if(!StringUtils.isEmpty(s)){
//                    ProduceEntity produceEntity = produceMapper.selectById(new Integer(s));
//                    name += produceEntity.getProduce_name() + ",";
//                }
//            }
//            order.setProducName(name);
//        }
        return list;
    }

    @Override
    public void send(Integer id) {
        OrderEntity order = orderMapper.selectById(id);

        String email = userMapper.selectEmailById(order.getUserId());
//        MailUtils.sendMail(email,"你好,你的商品已发货。","商城");
        try {
            SendmailUtil2.sendMall(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        orderMapper.updateState(id);
    }
}
