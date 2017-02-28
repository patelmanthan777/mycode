package com.example.administrator.myapplication.mvvm.bean;

import android.databinding.ObservableField;

/**
 * Created by xcy on 2017/1/10 0010.
 */

public class TestUser {
    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<String> age = new ObservableField<>();

    @Override
    public String toString() {
        return "TestUser{" +
                "name=" + name +
                ", age=" + age +
                '}';
    }
}
