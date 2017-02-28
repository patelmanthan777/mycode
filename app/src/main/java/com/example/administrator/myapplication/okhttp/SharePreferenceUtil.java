package com.example.administrator.myapplication.okhttp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by xcy on 2017/2/24 0024.
 */

public class SharePreferenceUtil {
    SharedPreferences sp;

    public SharePreferenceUtil(Context context) {
        sp = context.getApplicationContext().getSharedPreferences("wenjian", Context.MODE_PRIVATE);
    } // 根据键名提取键值

    public String get(String key) {
        String record = null;
        try {
            record = sp.getString(key, "");
        } catch (Exception ex) {
            Log.e("", ex.getMessage());
        } finally {
        }
        return record;
    }

    // 存储键对
    public void save(String key, String value) {
        try {
            SharedPreferences.Editor edit = sp.edit();
            edit.putString(key, value);
            edit.apply();
        } catch (Exception ex) {
            Log.e("", ex.getMessage());
        }
    }
}
