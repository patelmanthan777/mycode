package com.example.administrator.myapplication.service_process;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;


/**
 * 自定义闹钟管理类
 *
 * @author Administrator
 */
public class AlarmClockManager {

    public static final String ALARM_CLOCK_TYPE = "ALARM_CLOCK_TYPE";

    private final static int ONE_DAY_LOGOUT_REQUEST_CODE = 0X10;
    public final static int AUTO_UPLOAD_REQUEST_CODE = 0X11;
    public final static int AUTO_RESTART_REQUEST_CODE = 0X12;
    private final static int AUTO_DOWNLOAD_REQUEST_CODE = 0x13;
    private final static int AUTO_VER_UPDATE_REQUEST_CODE = 0x15;
    /**
     * 自动上传
     */
    public final static String AUTO_UPLOAD_ACTION = "auto_upload_action";
    /**
     * 自动重启
     */
    public final static String AUTO_RESTART_ACTION = "AUTO_RESTART_ACTION";
    public final static String AUTO_DELETE_LOGINDATA_ACTION = "auto_delete_logindata_action";
    /**
     * 定时下载 默认为1小时
     */
    public final static String AUTO_DOWNLOAD_ACTION = "auto_download_one_action";
    /**
     * 下载 箱所属运单号段
     */
    public final static String AUTO_DOWNLOAD_BILLS_COMP_ACTION = "auto_download_two_action";
    /**
     * 版本更新
     */
    public final static String AUTO_VER_UPDATE_ACTION = "auto_ver_update_action";
    private Context context;

    private static AlarmClockManager alarmClockManager;

    /**
     * 构造函数
     *
     * @param context
     */
    private AlarmClockManager(Context context) {
        this.context = context;

    }

    /**
     * 得到单例化对象
     *
     * @param context
     * @return
     */
    public synchronized static AlarmClockManager getInstance(Context context) {
        if (alarmClockManager == null) {
            alarmClockManager = new AlarmClockManager(context);
        }
        return alarmClockManager;
    }

    /**
     * 启动24小时登出
     */
    public void start24Alarm() {

        // Calendar calendar = Calendar.getInstance();
        // calendar.setTimeInMillis(System.currentTimeMillis());
        // calendar.add(Calendar.HOUR_OF_DAY, 24);
        //
        // PendingIntent sender = PendingIntent.getBroadcast(context,
        // ONE_DAY_LOGOUT_REQUEST_CODE,
        // buildIntent(BaseActivity.CLOSE_THIS_ACTIVITY_ACTION),
        // PendingIntent.FLAG_UPDATE_CURRENT);
        // AlarmManager alarm = (AlarmManager) context
        // .getSystemService(Context.ALARM_SERVICE);
        // alarm.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
        // sender);
        // System.out.println("AlarmManager login");

    }

    private Intent buildIntent(String action) {
        return new Intent(action);
    }

    /**
     * 取消一个定时闹钟
     */
    public void cance24lAlarm() {

        PendingIntent sender = PendingIntent.getBroadcast(context,
                ONE_DAY_LOGOUT_REQUEST_CODE, buildIntent(null),
                PendingIntent.FLAG_UPDATE_CURRENT);
        if (sender == null)
            return;
        AlarmManager alarm = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);
        alarm.cancel(sender);
        sender.cancel();

    }

    /**
     * 启动数据自动上传定时器
     * <p>
     * 5*1000 5秒
     */
    public void startAutoUploadAlarm(int time) {

        Intent intent = new Intent(AUTO_UPLOAD_ACTION);
        PendingIntent uploadSender = PendingIntent.getBroadcast(context,
                AUTO_UPLOAD_REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager uploadTimer = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        uploadTimer.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 2 * 3600 *
                1000, time * 60 * 1000, uploadSender);

    }

    public void closeUploadAlarm() {
        Intent intent = buildIntent(AUTO_UPLOAD_ACTION);
        PendingIntent uploadSender = PendingIntent.getBroadcast(context,
                AUTO_UPLOAD_REQUEST_CODE, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        if (uploadSender == null) {
            return;
        }
        AlarmManager uploadTimer = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);
        uploadTimer.cancel(uploadSender);
        uploadSender.cancel();
        uploadSender = null;
    }

    /**
     * 启动订单自动下载定时器
     */
    public void startAutoOrderAlarm(int time) {

		/*
         * if (time >= 5) { Intent intent =
		 * buildIntent(StoDownServer.UPDATE_ACTION);
		 * intent.putExtra(StoDownServer.SERVER_DOWN_STATUS, new int[] {
		 * MessageType.ORDER_DOWNLOAD_TYPE }); PendingIntent orderSender =
		 * PendingIntent.getBroadcast(context, AUTO_ORDER_REQUEST_CODE, intent,
		 * PendingIntent.FLAG_UPDATE_CURRENT); AlarmManager mgr = (AlarmManager)
		 * context .getSystemService(Context.ALARM_SERVICE);
		 * mgr.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
		 * + (time * 1000), time * 60 * 1000l, orderSender); }
		 */

    }

    /**
     * 关闭订单自动下载
     */
    public void closeAutoOrderAlarm() {
        /*
         * Intent intent = buildIntent(StoDownServer.UPDATE_ACTION);
		 * intent.putExtra(StoDownServer.SERVER_DOWN_STATUS, new int[] {
		 * MessageType.ORDER_DOWNLOAD_TYPE }); PendingIntent orderSender =
		 * PendingIntent.getBroadcast(context, AUTO_ORDER_REQUEST_CODE, intent,
		 * PendingIntent.FLAG_UPDATE_CURRENT); AlarmManager mgr = (AlarmManager)
		 * context .getSystemService(Context.ALARM_SERVICE);
		 * mgr.cancel(orderSender); orderSender.cancel(); orderSender = null;
		 */
    }

    public void deleteLoginData() {
        Intent intent = new Intent();
        intent.setAction(AUTO_DELETE_LOGINDATA_ACTION);
        PendingIntent rebootSender = PendingIntent.getBroadcast(context, 2,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        AlarmManager deleteDataAlarm = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);
        deleteDataAlarm.setRepeating(AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(), 24 * 60 * 60 * 1000, rebootSender);
    }

    /**
     * 开启自动下载定时
     */
    public void startAutoDownload(String action, int alarmHour) {
        Intent intent = new Intent();
        intent.setAction(action);
        PendingIntent pIntent = PendingIntent.getBroadcast(context,
                AUTO_DOWNLOAD_REQUEST_CODE, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager autodownloadAlarm = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);
        autodownloadAlarm.setRepeating(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() + alarmHour * 60 * 60 * 1000,
                alarmHour * 60 * 60 * 1000, pIntent);

    }

    /**
     * 开启版本更新定时
     */
    public void startAutoVerUpdate() {
        Intent intent = new Intent();
        intent.setAction(AUTO_VER_UPDATE_ACTION);
        PendingIntent pIntent = PendingIntent.getBroadcast(context,
                AUTO_VER_UPDATE_REQUEST_CODE, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager autodownloadAlarm = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);
        autodownloadAlarm.setRepeating(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() + 2 * 60 * 60 * 1000,
                2 * 60 * 60 * 1000, pIntent);

    }

    /**
     * 下午两点自动重启闹钟
     */
    public void startAutoRestart() {
        Intent intent = buildIntent(AUTO_RESTART_ACTION);
        PendingIntent pi = PendingIntent.getBroadcast(context, AUTO_RESTART_REQUEST_CODE, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 14); // 设置闹钟小时数
        c.set(Calendar.MINUTE, 0); // 设置闹钟的分钟数
        c.set(Calendar.SECOND, 0); // 设置闹钟的秒数
        c.set(Calendar.MILLISECOND, 0);
        AlarmManager alam = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alam.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);
    }
}
