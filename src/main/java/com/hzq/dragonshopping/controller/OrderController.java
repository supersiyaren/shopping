package com.hzq.dragonshopping.controller;

import com.hzq.dragonshopping.entity.OrderEntity;
import com.hzq.dragonshopping.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @Auther: wdd
 * @Date: 2019/12/15 10:44
 * @Description:
 */
@Controller
@RequestMapping("/order/")
public class OrderController {

    @Autowired
    private IOrderService orderService;


    @RequestMapping("toOrder.do")
    public Object tomanage(){
        return "order/list";
    }

    @ResponseBody
    @RequestMapping("doOrder.do")
    public String doOrder(HttpServletRequest request,String produceId,Integer orderNum,double totalPrice){
        if(request.getSession(true).getAttribute("user_id") != null){
            //获取存在session中的id
            Integer uid = (Integer) request.getSession(true).getAttribute("user_id");
            orderService.pay(uid,produceId,orderNum,totalPrice);
        }
        return "yes";
    }

    @ResponseBody
    @RequestMapping("getList.do")
    public List<OrderEntity> getList(HttpServletRequest request){
        if(request.getSession(true).getAttribute("user_id") != null){
            //获取存在session中的id
            Integer uid = (Integer) request.getSession(true).getAttribute("user_id");
            List<OrderEntity> orderEntities = orderService.getList(uid);
            return orderEntities;
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("getListAll.do")
    public List<OrderEntity> getListAll(){
        List<OrderEntity> orderEntities = orderService.getListAll();
        return orderEntities;
    }

    @ResponseBody
    @RequestMapping("send.do")
    public String send(int id){
        orderService.send(id);
        return "yes";
    }
}
