package com.example.administrator.myapplication.archite;

import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.util.Log;

import com.example.administrator.myapplication.archite.paging.db.ListItemBean;

/**
 * Created by xcy on 2017/12/8 0008.
 */

public class DataRepository {
    private final MyDatabase myDatabase;
    private static DataRepository sInstance;

    public DataRepository(MyDatabase myDatabase) {
        this.myDatabase = myDatabase;
    }

    public static DataRepository getInstance(final MyDatabase database) {
        if (sInstance == null) {
            synchronized (DataRepository.class) {
                if (sInstance == null) {
                    sInstance = new DataRepository(database);
                }
            }
        }
        return sInstance;
    }

    public LiveData<UserBean> getUser() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                UserBean userBean = new UserBean();
                userBean.setId("13094");
                userBean.setAge("431");
                userBean.setName("nihao");
                myDatabase.userDao().save(userBean);
            }
        }).start();
        return myDatabase.userDao().load("13094");
    }

    public LiveData<PagedList<ListItemBean>> getListItemBean() {
        Log.e("asdasd","getListItemBean");
        return new LivePagedListBuilder<>(myDatabase.mListItemDao()
                .findAllDS(), 20).build();
    }

    public void insertDataList() {

    }
}
