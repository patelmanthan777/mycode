package com.example.administrator.myapplication.test;

/**
 * Created by xcy on 2017/2/8 0008.
 */

public class B {
    private A mA;
    public B(A a){
        this.mA=a;
    }

    @Override
    public String toString() {
        return "B{" +
                "mA=" + mA +
                '}';
    }

    public A getA() {
        return mA;
    }

    public void setA(A a) {
        mA = a;
    }
}
