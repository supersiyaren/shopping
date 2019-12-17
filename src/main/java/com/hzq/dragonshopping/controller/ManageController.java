package com.hzq.dragonshopping.controller;

import com.hzq.dragonshopping.entity.History;
import com.hzq.dragonshopping.entity.OrderEntity;
import com.hzq.dragonshopping.entity.ProduceEntity;
import com.hzq.dragonshopping.service.IHistoryService;
import com.hzq.dragonshopping.service.IOrderService;
import com.hzq.dragonshopping.service.IProduceService;
import com.hzq.dragonshopping.untils.OssUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @Auther: wdd
 * @Date: 2019/12/14 18:20
 * @Description:
 */
@Controller
@RequestMapping("/manage/")
public class ManageController {

    @Autowired
    private OssUtil ossUtil;

    @Autowired
    private IProduceService produceService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IHistoryService historyService;

    @RequestMapping("tomanage.do")
    public Object tomanage(){
        return "account/product";
    }

    @RequestMapping("toadd.do")
    public Object toadd(){
        return "account/add";
    }

    @RequestMapping("toTotal.do")
    public Object toTotal(){
        return "account/total";
    }

    @RequestMapping("toOrder.do")
    public Object toOrder(){
        return "account/order";
    }

    @ResponseBody
    @RequestMapping("get.do")
    public List<OrderEntity> getListAll(){
        List<OrderEntity> orderEntities = orderService.getListAll();
        return orderEntities;
    }

    @RequestMapping("toHistory.do")
    public Object toHistory(){
        return "account/history";
    }

    @RequestMapping("toEdit.do")
    public Object toEdit(int id, Map<String, Object> map){
        ProduceEntity produceEntity = produceService.selectById(id);
        map.put("produceEntity", produceEntity);
        return "account/edit";
    }


    @ResponseBody
    @RequestMapping("getTotal.do")
    public List<ProduceEntity> getTotal(){
        List<ProduceEntity> produceEntities = produceService.showHotCommody();
        return produceEntities;
    }

    @ResponseBody
    @RequestMapping("getPrice.do")
    public List<Double> getPrice(){
        List<Double> prices = orderService.getPrice();
        return prices;
    }

    @ResponseBody
    @RequestMapping("getHistory.do")
    public List<History> getHistory(){
        List<History> histories = historyService.getAll();
        return histories;
    }

    @ResponseBody
    @RequestMapping("getNum.do")
    public List<Integer> getNum(){
        List<Integer> nums = orderService.getNum();
        return nums;
    }

    @RequestMapping("add.do")
    public Object add(MultipartFile produce_imgurl,
                                   String produce_name,
                                   Double produce_price,
                                   String produce_explain){
        ProduceEntity produceEntity = new ProduceEntity();
        produceEntity.setProduce_name(produce_name);
        produceEntity.setProduce_price(produce_price);
        produceEntity.setProduce_count(100);
//        produceEntity.setCreate_time(new Date().toString());
        produceEntity.setProduce_explain(produce_explain);
        produceEntity.setProduce_author("未知");
        produceEntity.setProduce_produce_sortnum("wx001");
        produceEntity.setProduce_shop_price(produce_price);
        String imgUrl = ossUtil.uploadImage(produce_imgurl, "image");
        produceEntity.setProduce_imgurl(imgUrl);
        produceService.add(produceEntity);
        return "account/add";
    }

    @RequestMapping("update.do")
    public Object update(int produce_id,MultipartFile produce_imgurl,
                      String produce_name,
                      Double produce_price,
                      String produce_explain){
        ProduceEntity produceEntity = new ProduceEntity();
        produceEntity.setProduce_id(produce_id);
        produceEntity.setProduce_name(produce_name);
        produceEntity.setProduce_price(produce_price);
        produceEntity.setProduce_count(100);
//        produceEntity.setCreate_time(new Date().toString());
        produceEntity.setProduce_explain(produce_explain);
        produceEntity.setProduce_author("未知");
        produceEntity.setProduce_produce_sortnum("wx001");
        produceEntity.setProduce_shop_price(produce_price);
        String imgUrl = ossUtil.uploadImage(produce_imgurl, "image");
        produceEntity.setProduce_imgurl(imgUrl);
        produceService.update(produceEntity);
        return "account/product";
    }

    @ResponseBody
    @RequestMapping("del.do")
    public String del(int id){
        produceService.del(id);
        return "yes";
    }

}
