package com.example.administrator.myapplication.zhengshu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.myapplication.R;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {
    String path = Environment.getExternalStorageDirectory().getAbsolutePath() +
            "/aaceshi";
    String url1 = "http://gtd.alicdn.com/tfscom/TB1B8INOVXXXXcpaXXXtKXbFXXX";
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_SHORT).show();
            GifDrawable gifFromAssets = null;
            try {
                ByteBuffer stream = (ByteBuffer) msg.obj;
                gifFromAssets = new GifDrawable(stream);
                GifImageView gifImageView = (GifImageView) findViewById(R.id.gifview);
                gifImageView.setImageDrawable(gifFromAssets);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_total);
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse
//                        ("xl://goods:8888/goodsDetail?goodsId=10011002"));
                //yingteli://apppay/payDetail?orderNum=xxxxx&orderAmount=xxxx&remainAmount=xxxx
                // &payAmount=xxxx
//                startActivity(intent);
//                startActivity(new Intent(MainActivity.this, PayDemoActivity.class));
                httpsconnection();
            }
        });
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse
                        ("fyfeng:"));
                startActivity(intent);
            }
        });
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                Message msg = new Message();
//                msg.obj = connection();
//                mHandler.sendMessage(msg);
//            }
//        };
//        Thread thread = new Thread(runnable);
//        thread.start();
    }

    public void httpsconnection() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
                    KeyStore keyStore = null;
                    keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
                    keyStore.load(null);
                    int index = 0;
                    InputStream certificate = getAssets().open("srca.cer");
                    String certificateAlias = Integer.toString(index++);
                    keyStore.setCertificateEntry(certificateAlias, certificateFactory
                            .generateCertificate(certificate));
                    if (certificate != null)
                        certificate.close();
                    SSLContext sslContext = SSLContext.getInstance("TLS");

                    TrustManagerFactory trustManagerFactory =
                            TrustManagerFactory.getInstance(TrustManagerFactory
                                    .getDefaultAlgorithm());

                    trustManagerFactory.init(keyStore);
                    sslContext.init(null, trustManagerFactory.getTrustManagers(), new
                            SecureRandom());
//                    HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
                    URL url = new URL("https://www.baidu.com");
                    HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
//                    con.connect();
                    con.setDoInput(true);
                    con.setDoOutput(true);
                    con.setRequestMethod("POST");
                    con.setUseCaches(false);
                    //设置为gbk可以解决服务器接收时读取的数据中文乱码问题
                    BufferedReader in = new BufferedReader(new InputStreamReader(
                            con.getInputStream()));
                    String line;
                    while ((line = in.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("asdasd", "" + e);
                }
            }
        }).start();
    }


    public void connection1() {
        try {
            URL url = new URL(url1);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.connect();
            InputStream is = httpURLConnection.getInputStream();

            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            File giff = new File(path + "/xx.gif");
            if (!giff.exists()) {
                giff.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(giff);
            byte b[] = new byte[2048];
            int i = 0;
            while ((i = is.read(b)) != -1) {
                fos.write(b, 0, i);
            }
            fos.flush();
            fos.close();
            is.close();

//                    GifDrawable gifFromAssets = new GifDrawable(b);
            Message msg = new Message();
            mHandler.sendMessage(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ByteBuffer connection() {
        try {
            URLConnection urlConnection = new URL(url1).openConnection();
            urlConnection.connect();
            final int contentLength = urlConnection.getContentLength();
            if (contentLength < 0) {
                throw new IOException("Content-Length not present");
            }
            ByteBuffer buffer = ByteBuffer.allocateDirect(contentLength);
            ReadableByteChannel channel = Channels.newChannel(urlConnection.getInputStream());
            while (buffer.remaining() > 0)
                channel.read(buffer);
            channel.close();
            return buffer;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public BufferedInputStream connection2() {
        try {
            URLConnection urlConnection = new URL(url1).openConnection();
            urlConnection.connect();
            final int contentLength = urlConnection.getContentLength();
            if (contentLength < 0) {
                throw new IOException("Content-Length not present");
            }
            InputStream inputStream = urlConnection.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(inputStream, contentLength);
            bis.read();
            return bis;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
