package com.hzq.dragonshopping.controller;

import com.hzq.dragonshopping.entity.ProduceEntity;
import com.hzq.dragonshopping.entity.UserEntity;
import com.hzq.dragonshopping.service.IUserService;
import com.hzq.dragonshopping.untils.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user/")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(IndexController.class);
    @Autowired
    private IUserService userService;


    /**
     * 登陆
     * @param userEntity
     * @return
     */
    @ResponseBody
    @RequestMapping("login.do")
    public Object userLogin(UserEntity userEntity, HttpServletRequest request){
        UserEntity t_user=userService.login(userEntity);
        Map<String,Object> res_map=new HashMap<String, Object>();
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        request = ((ServletRequestAttributes)ra).getRequest();
        if(t_user!=null){
            res_map.put("code", 1);
            res_map.put("msg", "登录成功");
            res_map.put("data", t_user);
            System.out.println("登陆成功");
            //session存储用户名、密码、用户id
            request.getSession(true).setAttribute("username",t_user.getUser_name());
            request.getSession(true).setAttribute("password",t_user.getUser_password());
            request.getSession(true).setAttribute("user_id",t_user.getUser_id());
            request.getSession(true).setAttribute("user",t_user);
            System.out.println(request.getSession(true).getAttribute("username").toString());
        }else{
            res_map.put("code", 0);
            res_map.put("msg", "登录失败");
            System.out.println("登陆失败");
        }
//        System.out.println(request.getSession(true).getAttribute("username").toString());
        return res_map;

    }


    /**
     * 注册
     * @param userEntity
     * @return
     */
    @ResponseBody
    @RequestMapping("regist.do")
    public AjaxResult regist(UserEntity userEntity){
        AjaxResult ajaxResult = new AjaxResult();
        try{
            userService.regist(userEntity);
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("注册成功");
        }catch(Exception e){
            e.printStackTrace();
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("注册失败");
        }
        return ajaxResult;
    }

    //注册是查询是否存在该用户
    @RequestMapping("/checkUser.do")
    @ResponseBody
    public AjaxResult checkUser(String user_name) {
        AjaxResult ajaxResult = new AjaxResult();
        int count=userService.checkUser(user_name);
        if(count==0){
            ajaxResult.setSuccess(true);
        }else{
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("当前用户已被注册!");
        }
        return ajaxResult;
    }

    /**
     * 个人中心
     * @return
     */
    @RequestMapping("center.do")
    public Object userCenter(HttpServletRequest request, HttpSession httpSession,Model model){
        return "account/index";
    }

    @RequestMapping("tologin.do")
    public Object tologin(){
        return "login";
    }

    @RequestMapping("alterPwd.do")
    public Object alterPwd(){
        return "alterPwd";
    }


    @RequestMapping("toUser.do")
    public Object toUser(Model model,HttpSession session){
        Integer user_id = (Integer) session.getAttribute("user_id");
        if(user_id == null){

        }
        UserEntity userEntity = userService.selectById(user_id);
        model.addAttribute("user", userEntity);
        return "myUser";
    }

    //确认密码
    @RequestMapping("/checkPwd.do")
    @ResponseBody
    public AjaxResult checkPwd(String user_password,HttpSession session){
        AjaxResult ajaxResult = new AjaxResult();
        String password = (String) session.getAttribute("password");
        if(password.equals(user_password)){
            ajaxResult.setSuccess(true);
        }else{
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("原密码错误");
        }
        return ajaxResult;
    }

    @RequestMapping("/doAlterpwd.do")
    @ResponseBody
    public AjaxResult alterpwd(String user_password,HttpSession session){
        AjaxResult ajaxResult = new AjaxResult();
        try{
            Integer user_id = (Integer) session.getAttribute("user_id");
            UserEntity user = new UserEntity();
            user.setUser_id(user_id);
            user.setUser_password(user_password);
            userService.setPassword(user);
            session.setAttribute("password",user.getUser_password());
            session.setAttribute("user_id",user.getUser_id());
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("更改密码成功");
        }catch(Exception e){
            e.printStackTrace();
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("更改密码失败");

        }
        return ajaxResult;
    }

    /**
     * 登出
     * @param request
     * @param httpSession
     * @return
     */
    @ResponseBody
    @RequestMapping("loginout.do")
    public Object loginOut(HttpServletRequest request, HttpSession httpSession){
        //获得session
        Map<String,String> map = new HashMap<>();
        if(request.getSession(true).getAttribute("user_id")!=null){
            RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            request = ((ServletRequestAttributes)ra).getRequest();
            Integer session_user_id = (Integer) request.getSession(true).getAttribute("user_id");
            map.put("session_user_id",session_user_id.toString());
            //清除session
            httpSession.invalidate();
            map.put("msgcode","1");
        }else if (request.getSession(true).getAttribute("user_id")==null){
            System.out.println("000000000");
            map.put("msgcode","0");
        }
        System.out.println("退出登录！");
        return map;
    }

    @ResponseBody
    @RequestMapping("isUser.do")
    public Object isUser(HttpServletRequest request, HttpSession session){
        //获得session
        Map<String,String> map = new HashMap<>();
        if(request.getSession(true).getAttribute("user_id")!=null){
            map.put("msgcode","1");
        }else {
            map.put("msgcode","0");
        }
        return map;
    }

    /**
     * 购买商品
     * @param request
     * @param userEntity
     * @param produceEntity
     * @return
     */
    @ResponseBody
    @RequestMapping("pay.do")
    public Object buyProduce(HttpServletRequest request, UserEntity userEntity, ProduceEntity produceEntity){

        Map<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("user_id") != null){
            //获取存在session中的id
            Integer uid = (Integer) request.getSession(true).getAttribute("user_id");
            userEntity.setUser_id(uid);
            logger.info("============================="+userEntity.toString()+"\n"+produceEntity.toString());
            map = userService.payProduce(produceEntity,userEntity);
            if (map.get("msgcode") == "1"){
                logger.info("code+msg"+"1支付成功");
            }else {
                logger.info("code+msg"+"0支付失败");
            }
        }else {
            map.put("msgcode","0000");
            logger.info("code+msg"+"0000未登录！");
        }
        return map;
    }


}
