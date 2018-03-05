package com.example.administrator.myapplication.okhttp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xcy on 2017/8/31 0031.
 */

public class ShangchaoActivity extends Activity {
    @BindView(R.id.textview)
    TextView mTextview;
    @BindView(R.id.linear)
    LinearLayout mLinear;
    @BindView(R.id.btnshangchao)
    Button mBtnshangchao;
    @BindView(R.id.btntuichao)
    Button mBtntuichao;
    @BindView(R.id.tv_shangchaojilu)
    TextView mTvShangchaojilu;
    @BindView(R.id.tv_shangchaoid)
    TextView mTvSHangchaoId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shangchao);
        ButterKnife.bind(this);
        String shangchaojilu = PreferenceUtils.getInstants(this).get("shangchaojilu");
        if (TextUtils.isEmpty(shangchaojilu)) {
            shangchaojilu = "没有值";
        }
        mTvShangchaojilu.setText(shangchaojilu);
    }

    @OnClick({R.id.btnshangchao, R.id.btntuichao, R.id.btnzidongdenglu, R.id.btnshangchaoliebiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnshangchao:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            HttpURLConnection connection = getHttpUrlConn(ShangchaoUrl.SHANGCHAO_NEW);
                            OutputStream os = connection.getOutputStream();
                            os.write(shangchaoPostParams().getBytes());
                            os.flush();
                            if (connection.getResponseCode() == 201 || connection.getResponseCode
                                    () == 200) {
                                InputStream inStrm = connection.getInputStream();
                                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                                byte[] b = new byte[2048];
                                int i = 0;
                                while ((i = inStrm.read(b)) != -1) {
                                    baos.write(b, 0, i);
                                }
                                baos.flush();
                                baos.close();
                                final String result = baos.toString();
                                Gson gson = new Gson();
                                final ShangchaoResulBean beannnn = gson.fromJson(result,
                                        ShangchaoResulBean.class);
                                mTextview.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            mTextview.setText(beannnn.toString());
                                            PreferenceUtils.getInstants(ShangchaoActivity.this).save
                                                    ("shangchaojilu", beannnn.getData()
                                                            .getBegin_time() + "||" + beannnn
                                                            .getData().getAddress());
                                        } catch (Exception e) {
                                            Toast.makeText(ShangchaoActivity.this, "出错了", Toast
                                                    .LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                break;
            case R.id.btnzidongdenglu:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            HttpURLConnection connection = getHttpUrlConn(ShangchaoUrl.Login_NEW);
                            OutputStream os = connection.getOutputStream();
                            os.write(zidongdengluParams().getBytes());
                            os.flush();
                            if (connection.getResponseCode() == 201 || connection.getResponseCode
                                    () == 200) {
                                InputStream inStrm = connection.getInputStream();
                                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                                byte[] b = new byte[2048];
                                int i = 0;
                                while ((i = inStrm.read(b)) != -1) {
                                    baos.write(b, 0, i);
                                }
                                baos.flush();
                                baos.close();
                                final String result = baos.toString();
                                Gson gson = new Gson();
                                final LoginResultBean beannnn = gson.fromJson(result,
                                        LoginResultBean.class);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            PreferenceUtils.getInstants(ShangchaoActivity.this).save
                                                    ("token", beannnn.getData().getPrivate_token());
                                            Toast.makeText(ShangchaoActivity.this, "登陆成功", Toast
                                                    .LENGTH_SHORT).show();
                                        } catch (Exception e) {
                                            Toast.makeText(ShangchaoActivity.this, "出错了", Toast
                                                    .LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
            case R.id.btntuichao:
//                X - Auth - Token:c1bff948c5f6056c
//                X - App - Token:b4f1921819f35e4eee6d477012cb8cdf
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            HttpURLConnection connection = getHttpUrlConn(ShangchaoUrl.SHANGCHAO_NEW);
                            OutputStream os = connection.getOutputStream();
                            os.write(tuichaoPostParams().getBytes());
                            os.flush();
                            if (connection.getResponseCode() == 201 || connection.getResponseCode
                                    () == 200) {
                                InputStream inStrm = connection.getInputStream();
                                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                                byte[] b = new byte[2048];
                                int i = 0;
                                while ((i = inStrm.read(b)) != -1) {
                                    baos.write(b, 0, i);
                                }
                                baos.flush();
                                baos.close();
                                final String result = baos.toString();
                                Gson gson = new Gson();
//                                final ShangchaoResulBean beannnn = gson.fromJson(result,
//                                        ShangchaoResulBean.class);
                                mTextview.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            Toast.makeText(ShangchaoActivity.this, "chenghong",
                                                    Toast
                                                            .LENGTH_SHORT).show();
                                        } catch (Exception e) {
                                            Toast.makeText(ShangchaoActivity.this, "出错了", Toast
                                                    .LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
            case R.id.btnshangchaoliebiao:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            URL url = new URL(ShangchaoUrl
                                    .SHANGCHAO_NEW + "?" + shangchaoliebiaoParams());
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                            connection.setRequestMethod("GET");
                            connection.setDoOutput(false);
                            connection.setDoInput(true);

                            connection.setRequestProperty("X-App-Token",
                                    "b4f1921819f35e4eee6d477012cb8cdf");
                            connection.setRequestProperty("X-Auth-Token", PreferenceUtils
                                    .getInstants(ShangchaoActivity.this).get
                                            ("token"));
                            connection.connect();
                            if (connection.getResponseCode() == 200 || connection.getResponseCode
                                    () == 201) {
                                InputStream inStrm = connection.getInputStream();
                                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                                byte[] b = new byte[2048];
                                int i = 0;
                                while ((i = inStrm.read(b)) != -1) {
                                    baos.write(b, 0, i);
                                }
                                baos.flush();
                                baos.close();
                                final String result = baos.toString();
                                Gson gson = new Gson();
                                final ShangchaoListResult beannnn = gson.fromJson(result,
                                        ShangchaoListResult.class);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            mTvSHangchaoId.setText(String.valueOf(beannnn.getData
                                                    ().getSign_ins
                                                    ().get(0).getId()));
                                            Toast.makeText(ShangchaoActivity.this, "获取成功", Toast
                                                    .LENGTH_SHORT).show();
                                        } catch (Exception e) {
                                            Toast.makeText(ShangchaoActivity.this, "出错了", Toast
                                                    .LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
        }
    }

    public HttpURLConnection getHttpUrlConn(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);

        connection.setRequestProperty("X-App-Token",
                "b4f1921819f35e4eee6d477012cb8cdf");
        connection.setRequestProperty("X-Auth-Token", PreferenceUtils.getInstants(this).get
                ("token"));
        return connection;
    }

    public static final String ADDRESS = "浙江省杭州市滨江区阡陌路靠近中国银行(杭州物联网产业园支行)";
    public static final String coordinates = "30.207743,120.22225";
    public static final String device_id = "c881c1486378466f";
    public static final String app_version = "1.7.1";

    public String shangchaoPostParams() {
        StringBuilder sb = new StringBuilder();
        sb.append("address=").append(ADDRESS)
                .append("&")
                .append("coordinates=")
                .append(coordinates)
                .append("&")
                .append("action_type=sign_in")
                .append("&")
                .append("device_id=")
                .append(device_id)
                .append("&")
                .append("uni_code=")
                .append(device_id)
                .append("&")
                .append("app=android")
                .append("&")
                .append("app_version=")
                .append(app_version)
        ;
        return sb.toString();
    }

    public String zidongdengluParams() {
        StringBuilder sb = new StringBuilder();
        sb.append("cell_phone=15068160426")
                .append("&")
                .append("password=xuchaoyang")
                .append("&")
                .append("uni_code=")
                .append(device_id)
                .append("&")
                .append("app=android")
                .append("&")
                .append("app_version=")
                .append(app_version)
        ;
        return sb.toString();
    }

    public String chongxindengluParams() {
        StringBuilder sb = new StringBuilder();
        sb.append("cell_phone=15068160426")
                .append("&")
                .append("password=xuchaoyang")
                .append("&")
                .append("uni_code=c881c1486378466f")
                .append("&")
                .append("app=android")
                .append("&")
                .append("app_version=1.7.1")
        ;
        return sb.toString();
    }

    public String shangchaoliebiaoParams() {
        StringBuilder sb = new StringBuilder();
        sb.append("page=1")
                .append("&")
                .append("per_page=30")
                .append("&")
                .append("uni_code=")
                .append(device_id)
                .append("&")
                .append("app=android")
                .append("&")
                .append("app_version=")
                .append(app_version)
                .append("&")
                .append("Current_Staff_Id=1782")
        ;
        return sb.toString();
    }
    public String tuichaoPostParams() {
        StringBuilder sb = new StringBuilder();
        sb.append("address=")
                .append(ADDRESS)
                .append("&")
                .append("coordinates=")
                .append(coordinates)
                .append("&")
                .append("action_type=sign_out")
                .append("&")
                .append("device_id=")
                .append(device_id)
                .append("&")
                .append("uni_code=")
                .append(device_id)
                .append("&")
                .append("app=android")
                .append("&")
                .append("app_version=")
                .append(app_version)
                .append("&")
                .append("id=").append(mTvSHangchaoId.getText().toString())
        ;
        return sb.toString();
    }
//    http://ishangchao.cn/api/v1/users/login
// 请求数据 {"password":"xuchaoyang","cell_phone":"15068160426"}
    //返回数据
//{"biz_action":0,"biz_msg":"","return_status":0,"data":{"nickname":"xcy","name":"许超阳",
// "cell_phone":"15068160426","private_token":"c1bff948c5f6056c",
// "im_password":"922dea992abf9707","im_id":"u-509-e495f3fb412b29d8d48ee596f6",
// "birthday":"1991-11-07","user_id":509,"staff_id":1782,"screen_name":"许超阳",
// "avatar_url":"http://img.ishangchao.cn/default.png","group_id":12,"group_name":"",
// "company_id":67,"company_name":"万色城","is_staff":true,"is_match":false,
// "is_eject_warning":false,"warning_content":"如果您是该公司员工，请联系HR核实姓名和手机号；如有问题，请联系上朝客服；",
// "sign_in_limit":600,"is_sync_address_list":true,"can_send_vedio":false}}


//上朝
//address	浙江省杭州市滨江区阡陌路靠近中国银行(杭州物联网产业园支行)
//    coordinates	30.207992,120.222118
//    action_type	sign_in
//    device_id	c881c1486378466f
//    uni_code	c881c1486378466f
//    app	android
//    app_version	1.7.0

//    {"biz_action":0,"biz_msg":"","return_status":0,"data":{"id":55115,
// "address":"浙江省杭州市滨江区阡陌路靠近中国银行(杭州物联网产业园支行)","coordinates":"30.207992,120.222118",
// "begin_time":"2017-08-31T10:34:52+08:00","end_time":""}}


    //tuichao
//    id	55114
//    address	浙江省杭州市滨江区阡陌路靠近中国银行(杭州物联网产业园支行)
//    coordinates	30.207992,120.222118
//    action_type	sign_out
//    device_id	c881c1486378466f
//    uni_code	c881c1486378466f
//    app	android
//    app_version	1.7.0

//    {"biz_action":0,"biz_msg":"","return_status":0,"data":{"id":55114,"address":"白马湖动漫广场",
// "coordinates":"120.221988,30.207933","begin_time":"2017-08-31T10:16:35+08:00",
// "end_time":"2017-08-31T10:33:41+08:00"}}
}
