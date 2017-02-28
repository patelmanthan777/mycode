package com.example.administrator.myapplication.test;

/**
 * Created by xcy on 2017/1/6 0006.
 */

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.net.ssl.HttpsURLConnection;


public class CopyOfDownloadTest {

    public static long total;
    public static long startTime;
    public static int alreadyTime = 0;
    public static final String url = "https://d25hz2nnwuc2tq.cloudfront" +
            ".net/images/image/cache/data/2016/10/19/1476866804-800x450-1080x608.webp";
    public static final int total_times = 100;

    private static Random threadLock = new Random();//线程锁

    public static void main(String[] args) throws IOException, InterruptedException {
        // TODO Auto-generated method stub
        new DownloadThread().start();

        while (1 < 2) {
            synchronized (threadLock) {//让主线程持有线程锁
                threadLock.wait();//锁住主线程
            }
            System.out.println("主线程解锁，准备重新new下载线程");
            new DownloadThread().start();
        }

    }

    public static class DownloadThread extends Thread {
        public boolean shouldStop = false;//超时后终结次进程

        public void run() {
            AlxDemonThread demonThread = new AlxDemonThread(this);
            demonThread.setDaemon(true);
            demonThread.start();
            for (int i = alreadyTime; i < total_times; i++) {
                if (shouldStop) return;
                try {
                    long cost = downloadNow(url, i);
                    if (shouldStop) return;
                    System.out.println("第" + i + "次耗时" + cost);

                } catch (Exception e) {
                    // TODO: handle exception
                    if (shouldStop) return;
                    System.out.println("下载失败");
                    e.printStackTrace();
                }
                if (shouldStop) return;
                alreadyTime++;
                total += System.currentTimeMillis() - startTime;
                startTime = 0;
            }
            if (!shouldStop) System.out.println("总耗时==" + total);
        }
    }

    public static class AlxDemonThread extends Thread {
        private DownloadThread mDownloadThread = null;

        public AlxDemonThread(DownloadThread t) {
            // TODO Auto-generated constructor stub
            this.mDownloadThread = t;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            while (2 > 0) {
                try {
                    Thread.sleep(1000);
//					System.out.println("守护线程还活着");
                } catch (InterruptedException e) {
                }
                if (alreadyTime == total_times) return;
                if (System.currentTimeMillis() - startTime > 30 * 1000) {
                    System.out.println("第" + alreadyTime + "超时了");
                    mDownloadThread.shouldStop = true;
                    mDownloadThread.interrupt();
                    alreadyTime++;
                    total += 30 * 1000;
                    synchronized (threadLock) {
                        threadLock.notify();
                    }
                    return;//停掉守护线程，防止再new一个子线程
                }
                //因为是守护线程，所以在下载线程结束后会自动停止
            }
        }
    }

    public static long downloadNow(String strUrl, int times) throws IOException {
        startTime = System.currentTimeMillis();
        URL url = new URL(strUrl);
        HttpsURLConnection httpURLConnection = (HttpsURLConnection) url.openConnection();
        httpURLConnection.setDoInput(true);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
        httpURLConnection.setRequestProperty("Charset", "UTF-8");
        httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" +
                "******");
        httpURLConnection.connect();
//        System.out.println("响应码是::"+httpURLConnection.getResponseCode());
        File pictureFile = new File("d:/speed/test" + times + ".jpg");
        BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
        FileOutputStream outputStream = new FileOutputStream(pictureFile);
        byte[] buffer = new byte[1024];
        int len = 0;
        BufferedOutputStream out = new BufferedOutputStream(outputStream);
        while ((len = bis.read(buffer)) != -1) {
            if (System.currentTimeMillis() - startTime > 30 * 1000)
                throw new RuntimeException("超时");
            out.write(buffer, 0, len);
        }
        out.flush();
        out.close();
        bis.close();
        return System.currentTimeMillis() - startTime;
    }

}
