package com.hzq.dragonshopping.entity;


import java.util.Date;

/**
 * @Auther: wdd
 * @Date: 2019/12/12 20:37
 * @Description:
 */
public class History {
    private Integer id;
    /**
     * 商品标题
     */
    private String title;

    /**
     * 商城价
     */
    private double price;

    private Integer userId;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
