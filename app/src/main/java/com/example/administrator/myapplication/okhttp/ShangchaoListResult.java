package com.example.administrator.myapplication.okhttp;

import java.util.List;

/**
 * Created by xcy on 2017/8/31 0031.
 */

public class ShangchaoListResult {

    /**
     * biz_action : 0
     * biz_msg : null
     * return_status : 0
     * data : {"sign_ins":[{"id":55121,"address":"浙江省杭州市滨江区阡陌路靠近中国银行(杭州物联网产业园支行)",
     * "coordinates":"30.207992,120.222118","begin_time":"2017-08-31T11:21:21+08:00",
     * "end_time":"2017-08-31T11:22:45+08:00"},{"id":55120,"address":"浙江省杭州市滨江区阡陌路靠近中国银行
     * (杭州物联网产业园支行)","coordinates":"30.207992,120.222118",
     * "begin_time":"2017-08-31T11:20:47+08:00","end_time":"2017-08-31T11:21:05+08:00"},
     * {"id":55119,"address":"浙江省杭州市滨江区阡陌路靠近中国银行(杭州物联网产业园支行)","coordinates":"30.207992,
     * 120.222118","begin_time":"2017-08-31T11:20:14+08:00",
     * "end_time":"2017-08-31T11:20:23+08:00"},{"id":55118,"address":"浙江省杭州市滨江区阡陌路靠近中国银行
     * (杭州物联网产业园支行)","coordinates":"30.207992,120.222118",
     * "begin_time":"2017-08-31T11:19:19+08:00","end_time":"2017-08-31T11:20:10+08:00"},
     * {"id":55117,"address":"浙江省杭州市滨江区阡陌路靠近中国银行(杭州物联网产业园支行)","coordinates":"30.207992,
     * 120.222118","begin_time":"2017-08-31T11:18:40+08:00",
     * "end_time":"2017-08-31T11:19:09+08:00"},{"id":55116,"address":"浙江省杭州市滨江区阡陌路靠近中国银行
     * (杭州物联网产业园支行)","coordinates":"30.207992,120.222118",
     * "begin_time":"2017-08-31T11:17:44+08:00","end_time":"2017-08-31T11:18:38+08:00"},
     * {"id":55115,"address":"浙江省杭州市滨江区阡陌路靠近中国银行(杭州物联网产业园支行)","coordinates":"30.207992,
     * 120.222118","begin_time":"2017-08-31T10:34:52+08:00",
     * "end_time":"2017-08-31T10:43:52+08:00"},{"id":55114,"address":"白马湖动漫广场",
     * "coordinates":"120.221988,30.207933","begin_time":"2017-08-31T10:16:35+08:00",
     * "end_time":"2017-08-31T10:33:41+08:00"},{"id":55082,"address":"白马湖动漫广场",
     * "coordinates":"120.223380,30.202773","begin_time":"2017-08-31T09:03:25+08:00",
     * "end_time":"2017-08-31T10:15:00+08:00"},{"id":54846,"address":"白马湖动漫广场",
     * "coordinates":"120.223442,30.203720","begin_time":"2017-08-30T08:57:29+08:00",
     * "end_time":"2017-08-30T18:09:54+08:00"},{"id":54645,"address":"白马湖动漫广场",
     * "coordinates":"120.223799,30.202826","begin_time":"2017-08-29T08:59:07+08:00",
     * "end_time":"2017-08-29T18:04:40+08:00"},{"id":54435,"address":"白马湖动漫广场",
     * "coordinates":"120.223000,30.202497","begin_time":"2017-08-28T09:00:57+08:00",
     * "end_time":""},{"id":54158,"address":"白马湖动漫广场","coordinates":"120.223566,30.202505",
     * "begin_time":"2017-08-25T09:01:00+08:00","end_time":"2017-08-25T18:40:03+08:00"},
     * {"id":53972,"address":"白马湖动漫广场","coordinates":"120.222006,30.207934","begin_time":"",
     * "end_time":"2017-08-24T18:05:33+08:00"},{"id":53738,"address":"白马湖动漫广场",
     * "coordinates":"120.221528,30.208397","begin_time":"2017-08-23T13:31:44+08:00",
     * "end_time":"2017-08-23T18:02:07+08:00"},{"id":53423,"address":"白马湖动漫广场",
     * "coordinates":"120.223382,30.203218","begin_time":"2017-08-22T08:58:11+08:00",
     * "end_time":"2017-08-22T18:20:21+08:00"},{"id":53233,"address":"白马湖动漫广场",
     * "coordinates":"120.223030,30.206130","begin_time":"2017-08-21T08:59:47+08:00",
     * "end_time":"2017-08-21T18:13:32+08:00"},{"id":53010,"address":"白马湖动漫广场",
     * "coordinates":"120.223344,30.203254","begin_time":"2017-08-18T09:00:47+08:00",
     * "end_time":"2017-08-18T18:12:43+08:00"},{"id":52835,"address":"白马湖动漫广场",
     * "coordinates":"120.223976,30.206418","begin_time":"2017-08-17T11:04:24+08:00",
     * "end_time":"2017-08-17T18:13:05+08:00"},{"id":52564,"address":"白马湖动漫广场",
     * "coordinates":"120.225933,30.204921","begin_time":"2017-08-16T08:57:46+08:00",
     * "end_time":"2017-08-16T18:06:49+08:00"},{"id":52314,"address":"白马湖动漫广场",
     * "coordinates":"120.225769,30.204752","begin_time":"2017-08-15T08:58:43+08:00",
     * "end_time":"2017-08-15T18:18:07+08:00"},{"id":52093,"address":"白马湖动漫广场",
     * "coordinates":"120.225980,30.204629","begin_time":"2017-08-14T08:56:38+08:00",
     * "end_time":"2017-08-14T18:03:38+08:00"},{"id":51928,"address":"白马湖动漫广场",
     * "coordinates":"120.224165,30.206272","begin_time":"2017-08-12T10:39:54+08:00",
     * "end_time":"2017-08-12T14:25:44+08:00"},{"id":51904,"address":"白马湖动漫广场",
     * "coordinates":"120.221572,30.208678","begin_time":"2017-08-11T09:07:15+08:00",
     * "end_time":"2017-08-11T18:35:57+08:00"},{"id":51670,"address":"白马湖动漫广场",
     * "coordinates":"120.223597,30.202776","begin_time":"2017-08-10T09:02:47+08:00",
     * "end_time":"2017-08-10T21:16:22+08:00"},{"id":51481,"address":"白马湖动漫广场",
     * "coordinates":"120.225825,30.206360","begin_time":"",
     * "end_time":"2017-08-09T18:23:08+08:00"},{"id":51253,"address":"白马湖动漫广场",
     * "coordinates":"120.221314,30.208363","begin_time":"2017-08-08T13:44:49+08:00",
     * "end_time":"2017-08-08T18:14:45+08:00"},{"id":51012,"address":"白马湖动漫广场",
     * "coordinates":"120.223994,30.202792","begin_time":"2017-08-07T09:03:32+08:00",
     * "end_time":"2017-08-07T18:31:45+08:00"},{"id":50746,"address":"白马湖动漫广场",
     * "coordinates":"120.225951,30.205045","begin_time":"2017-08-04T09:05:40+08:00",
     * "end_time":"2017-08-04T18:12:36+08:00"},{"id":50491,"address":"白马湖动漫广场",
     * "coordinates":"120.223123,30.203046","begin_time":"2017-08-03T08:57:52+08:00",
     * "end_time":"2017-08-03T19:50:56+08:00"}],"page":"1","per_page":"30","total_count":224,
     * "num_pages":8}
     */

    private int biz_action;
    private Object biz_msg;
    private int return_status;
    private DataBean data;

    public int getBiz_action() {
        return biz_action;
    }

    public void setBiz_action(int biz_action) {
        this.biz_action = biz_action;
    }

    public Object getBiz_msg() {
        return biz_msg;
    }

    public void setBiz_msg(Object biz_msg) {
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
         * sign_ins : [{"id":55121,"address":"浙江省杭州市滨江区阡陌路靠近中国银行(杭州物联网产业园支行)",
         * "coordinates":"30.207992,120.222118","begin_time":"2017-08-31T11:21:21+08:00",
         * "end_time":"2017-08-31T11:22:45+08:00"},{"id":55120,"address":"浙江省杭州市滨江区阡陌路靠近中国银行
         * (杭州物联网产业园支行)","coordinates":"30.207992,120.222118",
         * "begin_time":"2017-08-31T11:20:47+08:00","end_time":"2017-08-31T11:21:05+08:00"},
         * {"id":55119,"address":"浙江省杭州市滨江区阡陌路靠近中国银行(杭州物联网产业园支行)","coordinates":"30.207992,
         * 120.222118","begin_time":"2017-08-31T11:20:14+08:00",
         * "end_time":"2017-08-31T11:20:23+08:00"},{"id":55118,"address":"浙江省杭州市滨江区阡陌路靠近中国银行
         * (杭州物联网产业园支行)","coordinates":"30.207992,120.222118",
         * "begin_time":"2017-08-31T11:19:19+08:00","end_time":"2017-08-31T11:20:10+08:00"},
         * {"id":55117,"address":"浙江省杭州市滨江区阡陌路靠近中国银行(杭州物联网产业园支行)","coordinates":"30.207992,
         * 120.222118","begin_time":"2017-08-31T11:18:40+08:00",
         * "end_time":"2017-08-31T11:19:09+08:00"},{"id":55116,"address":"浙江省杭州市滨江区阡陌路靠近中国银行
         * (杭州物联网产业园支行)","coordinates":"30.207992,120.222118",
         * "begin_time":"2017-08-31T11:17:44+08:00","end_time":"2017-08-31T11:18:38+08:00"},
         * {"id":55115,"address":"浙江省杭州市滨江区阡陌路靠近中国银行(杭州物联网产业园支行)","coordinates":"30.207992,
         * 120.222118","begin_time":"2017-08-31T10:34:52+08:00",
         * "end_time":"2017-08-31T10:43:52+08:00"},{"id":55114,"address":"白马湖动漫广场",
         * "coordinates":"120.221988,30.207933","begin_time":"2017-08-31T10:16:35+08:00",
         * "end_time":"2017-08-31T10:33:41+08:00"},{"id":55082,"address":"白马湖动漫广场",
         * "coordinates":"120.223380,30.202773","begin_time":"2017-08-31T09:03:25+08:00",
         * "end_time":"2017-08-31T10:15:00+08:00"},{"id":54846,"address":"白马湖动漫广场",
         * "coordinates":"120.223442,30.203720","begin_time":"2017-08-30T08:57:29+08:00",
         * "end_time":"2017-08-30T18:09:54+08:00"},{"id":54645,"address":"白马湖动漫广场",
         * "coordinates":"120.223799,30.202826","begin_time":"2017-08-29T08:59:07+08:00",
         * "end_time":"2017-08-29T18:04:40+08:00"},{"id":54435,"address":"白马湖动漫广场",
         * "coordinates":"120.223000,30.202497","begin_time":"2017-08-28T09:00:57+08:00",
         * "end_time":""},{"id":54158,"address":"白马湖动漫广场","coordinates":"120.223566,30.202505",
         * "begin_time":"2017-08-25T09:01:00+08:00","end_time":"2017-08-25T18:40:03+08:00"},
         * {"id":53972,"address":"白马湖动漫广场","coordinates":"120.222006,30.207934","begin_time":"",
         * "end_time":"2017-08-24T18:05:33+08:00"},{"id":53738,"address":"白马湖动漫广场",
         * "coordinates":"120.221528,30.208397","begin_time":"2017-08-23T13:31:44+08:00",
         * "end_time":"2017-08-23T18:02:07+08:00"},{"id":53423,"address":"白马湖动漫广场",
         * "coordinates":"120.223382,30.203218","begin_time":"2017-08-22T08:58:11+08:00",
         * "end_time":"2017-08-22T18:20:21+08:00"},{"id":53233,"address":"白马湖动漫广场",
         * "coordinates":"120.223030,30.206130","begin_time":"2017-08-21T08:59:47+08:00",
         * "end_time":"2017-08-21T18:13:32+08:00"},{"id":53010,"address":"白马湖动漫广场",
         * "coordinates":"120.223344,30.203254","begin_time":"2017-08-18T09:00:47+08:00",
         * "end_time":"2017-08-18T18:12:43+08:00"},{"id":52835,"address":"白马湖动漫广场",
         * "coordinates":"120.223976,30.206418","begin_time":"2017-08-17T11:04:24+08:00",
         * "end_time":"2017-08-17T18:13:05+08:00"},{"id":52564,"address":"白马湖动漫广场",
         * "coordinates":"120.225933,30.204921","begin_time":"2017-08-16T08:57:46+08:00",
         * "end_time":"2017-08-16T18:06:49+08:00"},{"id":52314,"address":"白马湖动漫广场",
         * "coordinates":"120.225769,30.204752","begin_time":"2017-08-15T08:58:43+08:00",
         * "end_time":"2017-08-15T18:18:07+08:00"},{"id":52093,"address":"白马湖动漫广场",
         * "coordinates":"120.225980,30.204629","begin_time":"2017-08-14T08:56:38+08:00",
         * "end_time":"2017-08-14T18:03:38+08:00"},{"id":51928,"address":"白马湖动漫广场",
         * "coordinates":"120.224165,30.206272","begin_time":"2017-08-12T10:39:54+08:00",
         * "end_time":"2017-08-12T14:25:44+08:00"},{"id":51904,"address":"白马湖动漫广场",
         * "coordinates":"120.221572,30.208678","begin_time":"2017-08-11T09:07:15+08:00",
         * "end_time":"2017-08-11T18:35:57+08:00"},{"id":51670,"address":"白马湖动漫广场",
         * "coordinates":"120.223597,30.202776","begin_time":"2017-08-10T09:02:47+08:00",
         * "end_time":"2017-08-10T21:16:22+08:00"},{"id":51481,"address":"白马湖动漫广场",
         * "coordinates":"120.225825,30.206360","begin_time":"",
         * "end_time":"2017-08-09T18:23:08+08:00"},{"id":51253,"address":"白马湖动漫广场",
         * "coordinates":"120.221314,30.208363","begin_time":"2017-08-08T13:44:49+08:00",
         * "end_time":"2017-08-08T18:14:45+08:00"},{"id":51012,"address":"白马湖动漫广场",
         * "coordinates":"120.223994,30.202792","begin_time":"2017-08-07T09:03:32+08:00",
         * "end_time":"2017-08-07T18:31:45+08:00"},{"id":50746,"address":"白马湖动漫广场",
         * "coordinates":"120.225951,30.205045","begin_time":"2017-08-04T09:05:40+08:00",
         * "end_time":"2017-08-04T18:12:36+08:00"},{"id":50491,"address":"白马湖动漫广场",
         * "coordinates":"120.223123,30.203046","begin_time":"2017-08-03T08:57:52+08:00",
         * "end_time":"2017-08-03T19:50:56+08:00"}]
         * page : 1
         * per_page : 30
         * total_count : 224
         * num_pages : 8
         */

        private String page;
        private String per_page;
        private int total_count;
        private int num_pages;
        private List<SignInsBean> sign_ins;

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public String getPer_page() {
            return per_page;
        }

        public void setPer_page(String per_page) {
            this.per_page = per_page;
        }

        public int getTotal_count() {
            return total_count;
        }

        public void setTotal_count(int total_count) {
            this.total_count = total_count;
        }

        public int getNum_pages() {
            return num_pages;
        }

        public void setNum_pages(int num_pages) {
            this.num_pages = num_pages;
        }

        public List<SignInsBean> getSign_ins() {
            return sign_ins;
        }

        public void setSign_ins(List<SignInsBean> sign_ins) {
            this.sign_ins = sign_ins;
        }

        public static class SignInsBean {
            /**
             * id : 55121
             * address : 浙江省杭州市滨江区阡陌路靠近中国银行(杭州物联网产业园支行)
             * coordinates : 30.207992,120.222118
             * begin_time : 2017-08-31T11:21:21+08:00
             * end_time : 2017-08-31T11:22:45+08:00
             */

            private String id;
            private String address;
            private String coordinates;
            private String begin_time;
            private String end_time;

            public String getId() {
                return id;
            }

            public void setId(String id) {
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
}
