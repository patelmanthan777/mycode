package com.example.administrator.myapplication.test;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by xcy on 2017/1/5 0005.
 */

public class test {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            String xx = "aa" + i;
            list.add(xx);
        }
        List<String> listnew = Collections.unmodifiableList(list);
        for (String a : listnew) {
            System.out.println(a);
        }
    }
}
