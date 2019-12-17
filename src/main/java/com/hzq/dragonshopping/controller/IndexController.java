package com.hzq.dragonshopping.controller;

import com.hzq.dragonshopping.entity.Admin;
import com.hzq.dragonshopping.entity.OrderEntity;
import com.hzq.dragonshopping.service.IOrderService;
import com.hzq.dragonshopping.service.IProduceCategoryService;
import com.hzq.dragonshopping.service.IProduceService;
import com.hzq.dragonshopping.untils.AjaxResult;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
public class IndexController {

    private final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private IProduceService produceService;
    @Autowired
    private IProduceCategoryService iProduceCategoryService;
    @Autowired
    private IOrderService orderService;


    /**
     * 热门商品
     * @param model
     * @return
     */
    @RequestMapping("index.do")
    public String viewHotCommody(Model model){
        //热门商品信息
        Model produce = model.addAttribute("produceList", produceService.showHotCommody());
        //所有商品的分类信息
        model.addAttribute("produceCategory",iProduceCategoryService.selectProduceCategoryExample());
        logger.info("数据");
        return "index";
    }

    @ResponseBody
    @RequestMapping("get.do")
    public HashMap<String, String> getListAll(){
//        List<OrderEntity> orderEntities = orderService.getListAll();
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("test","test");
        return map;
    }

    @ResponseBody
    @RequestMapping("hello.do")
    public Object hello(){
        List<OrderEntity> orderEntities = orderService.getListAll();
        return orderEntities;
    }

    @RequestMapping("logoutAdmin.do")
    public String logoutAdmin(HttpSession session,Model model){
        session.removeAttribute("admin");
        //热门商品信息
        Model produce = model.addAttribute("produceList", produceService.showHotCommody());
        //所有商品的分类信息
        model.addAttribute("produceCategory",iProduceCategoryService.selectProduceCategoryExample());
        return "index";
    }

    @PostMapping("/loginAdmin.do")
    @ResponseBody
    public AjaxResult login(@RequestParam("userName") String userName,
                            @RequestParam("password") String password,
                            HttpSession session) {
        AjaxResult ajaxResult = new AjaxResult();
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("用户名或密码不能为空");
            return ajaxResult;
        }
        if (userName.equals("admin") && password.equals("admin")) {
            Admin admin = new Admin();
            admin.setUsername("admin");
            admin.setPassword("admin");
            session.setAttribute("admin",admin);
            //session过期时间设置为7200秒 即两小时
            session.setMaxInactiveInterval(60 * 60 * 2);

            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("登录成功");
        } else {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("用户名或密码错误");
        }
        return ajaxResult;
    }




}
