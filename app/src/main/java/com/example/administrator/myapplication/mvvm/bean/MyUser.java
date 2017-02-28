package com.example.administrator.myapplication.mvvm.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.administrator.myapplication.BR;

/**
 * Created by xcy on 2017/1/6 0006.
 */

public class MyUser extends BaseObservable {
    private String mName;
    private String mAge;
    private String mAddress;

    @Bindable
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getAge() {
        return mAge;
    }

    public void setAge(String age) {
        mAge = age;
        notifyPropertyChanged(BR.age);
    }

    @Bindable
    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
        notifyPropertyChanged(BR.address);
    }
}
