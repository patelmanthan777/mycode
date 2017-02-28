package com.example.administrator.myapplication;

import com.google.gson.Gson;

/**
 * Created by davidtps on 2016年12月30日 11时42分.
 */

public class PayStatusEvent {
    public static final String PAYTYPE_WEIXIN = "wxpay";//微信支付
    public static final String PAYTYPE_ALI = "alipay";//支付宝支付
    public static final String PAYTYPE_UNION = "unionpay";//银联支付
    public static final String PAYRESULT_OK = "1";//支付成功
    public static final String PAYRESULT_CANCEL = "2";//支付取消
    public static final String PAYRESULT_FAILE = "0";//支付失败

    private String payStatus;
    private String orderNum;
    private String payType;
    private String platform = "android";

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    //{"orderNum":"订单编号",payStatus:"0：失败，1：成功，2：取消支付","payType":"支付类型：alipay,wxpay,unionpay",
    // "platform":"android"}
    public PayStatusEvent() {
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }
}
