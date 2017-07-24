package com.example.administrator.myapplication.service_process.mutiprocess;

/**
 * Created by xcy on 2017/5/25 0025.
 */

public class ServiceEvent {
    private String nanme;

    public String getNanme() {
        return nanme;
    }

    public void setNanme(String nanme) {
        this.nanme = nanme;
    }

    @Override
    public String toString() {
        return "ServiceEvent{" +
                "nanme='" + nanme + '\'' +
                '}';
    }
}
