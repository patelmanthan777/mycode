package com.example.administrator.myapplication.service_process.mutiprocess;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xcy on 2017/3/29 0029.
 */

public class ProcessBean implements Parcelable {
    private String name;

    public ProcessBean() {
    }

    protected ProcessBean(Parcel in) {
        name = in.readString();
    }

    public static final Creator<ProcessBean> CREATOR = new Creator<ProcessBean>() {
        @Override
        public ProcessBean createFromParcel(Parcel in) {
            return new ProcessBean(in);
        }

        @Override
        public ProcessBean[] newArray(int size) {
            return new ProcessBean[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }
}
