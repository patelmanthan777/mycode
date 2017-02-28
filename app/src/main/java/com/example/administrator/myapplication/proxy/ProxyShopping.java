package com.example.administrator.myapplication.proxy;

/**
 * Created by xcy on 2017/1/3 0003.
 */

public class ProxyShopping implements Shopping {
    Shopping base;

    public ProxyShopping(Shopping base) {
        this.base = base;
    }

    @Override
    public void doShopping(String xx) {
        System.out.println(String.format("花了%s块钱", xx));
        base.doShopping("asdasd");
    }
}
