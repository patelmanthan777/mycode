package com.example.administrator.myapplication.proxy;

/**
 * Created by xcy on 2017/1/3 0003.
 */

public class ShoppingImpl implements Shopping {
    @Override
    public void doShopping(String xx) {
        System.out.println("逛淘宝 ,逛商场,买买买!!");
        System.out.println(String.format("花了%s块钱", xx));
    }
}