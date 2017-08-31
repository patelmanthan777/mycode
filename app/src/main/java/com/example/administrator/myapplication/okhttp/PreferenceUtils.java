package com.example.administrator.myapplication.okhttp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by xcy on 2017/8/31 0031.
 */

public class PreferenceUtils {
    Context mContext;
    SharedPreferences sp;

    private static PreferenceUtils instants;

//	/**
//	 *
//	 * @param pContext
//	 * @param pName   preference文件名称
//	 */
//    public PreferenceUtils(Context pContext, String pName) {
//        mContext = pContext;
//        sp = pContext.getSharedPreferences(pName, Context.MODE_PRIVATE);
//    }

    private PreferenceUtils(Context context) {
        if (sp == null) {
            sp = context.getApplicationContext().getSharedPreferences("wode", Context
                    .MODE_PRIVATE);
        }
    }

    public static PreferenceUtils getInstants(Context context) {
        if (instants == null) {
            instants = new PreferenceUtils(context);
        }

        return instants;
    }

    public void init(Context pContext, String name) {

    }


    protected void deleteDB(String name) throws Exception {
        sp.edit().remove(name);
    }

    // 根据键名提取键值
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
            edit.commit();
        } catch (Exception ex) {
            Log.e("", ex.getMessage());
        }
    }

    // 在原有基础上增加值
    public void saveAndApply(String key, String value) {
        try {
            SharedPreferences.Editor edit = sp.edit();
            edit.putString(key, sp.getString(key, "") + value);
            edit.commit();
        } catch (Exception ex) {
            Log.e("", ex.getMessage());
        }
    }

    // 删除存储值
    public void delete(String key) {
        try {
            SharedPreferences.Editor edit = sp.edit();
            edit.remove(key);
            edit.commit();
        } catch (Exception ex) {
            Log.e("", ex.getMessage());
        }
    }


    // 根据键名提取键值
    public String get(Context context, String key) {
        if (sp == null) {
            init(context, null);
        }

        return sp.getString(key, "");
    }

    // 存储键对
    public void save(Context context, String key, String value) {

        if (sp == null) {
            init(context, null);
        }
        try {
            sp.edit().putString(key, value).commit();
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    // 删除存储值
    public void delete(Context context, String key) {
        if (sp == null) {
            init(context, null);
        }
        sp.edit().remove(key).commit();
    }

}
