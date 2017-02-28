package com.example.administrator.myapplication.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by xcy on 2017/1/4 0004.
 * 支付
 */

public class PayProxyManager implements PayInterface {
    public static final String PAY_ALI = "ali";

    private PayInterface mPayInterface;

    @Override
    public void pay(String type) {
        System.out.println("成功");
        switch (type) {
            case PAY_ALI:

                break;
        }
    }

    public void newProxyInstance() {
        mPayInterface = (PayInterface) Proxy.newProxyInstance(PayInterface.class
                .getClassLoader(), new Class[]{PayInterface.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("正在开始");
                String object = (String) method.invoke(PayProxyManager.this, args);
                System.out.println("wancheng");
                return object;
            }
        });
    }

    public void startPay(String type) {
        if (mPayInterface == null) {
            newProxyInstance();
        }
        mPayInterface.pay(type);
    }
}
