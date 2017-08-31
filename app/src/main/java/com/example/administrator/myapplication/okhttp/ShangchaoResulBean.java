package com.example.administrator.myapplication.okhttp;

/**
 * Created by xcy on 2017/8/31 0031.
 */

public class ShangchaoResulBean {

    /**
     * biz_action : 0
     * biz_msg :
     * return_status : 0
     * data : {"id":55115,"address":"浙江省杭州市滨江区阡陌路靠近中国银行(杭州物联网产业园支行)","coordinates":"30.207992,
     * 120.222118","begin_time":"2017-08-31T10:34:52+08:00","end_time":""}
     */

    private int biz_action;
    private String biz_msg;
    private int return_status;
    private DataBean data;

    @Override
    public String toString() {
        return "ShangchaoResulBean{" +
                "biz_action=" + biz_action +
                ", biz_msg='" + biz_msg + '\'' +
                ", return_status=" + return_status +
                ", data=" + data +
                '}';
    }

    public int getBiz_action() {
        return biz_action;
    }

    public void setBiz_action(int biz_action) {
        this.biz_action = biz_action;
    }

    public String getBiz_msg() {
        return biz_msg;
    }

    public void setBiz_msg(String biz_msg) {
        this.biz_msg = biz_msg;
    }

    public int getReturn_status() {
        return return_status;
    }

    public void setReturn_status(int return_status) {
        this.return_status = return_status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 55115
         * address : 浙江省杭州市滨江区阡陌路靠近中国银行(杭州物联网产业园支行)
         * coordinates : 30.207992,120.222118
         * begin_time : 2017-08-31T10:34:52+08:00
         * end_time :
         */

        private int id;
        private String address;
        private String coordinates;
        private String begin_time;
        private String end_time;

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", address='" + address + '\'' +
                    ", coordinates='" + coordinates + '\'' +
                    ", begin_time='" + begin_time + '\'' +
                    ", end_time='" + end_time + '\'' +
                    '}';
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(String coordinates) {
            this.coordinates = coordinates;
        }

        public String getBegin_time() {
            return begin_time;
        }

        public void setBegin_time(String begin_time) {
            this.begin_time = begin_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }
    }
}
