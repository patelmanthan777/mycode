package com.example.administrator.myapplication.archite.paging;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.example.administrator.myapplication.archite.paging.db.ListItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xcy on 2018/3/5 0005.
 */

public class ListItemViewModel extends BaseViewModel {
    private LiveData<PagedList<ListItemBean>> mListLiveData;


    public ListItemViewModel(@NonNull Application application) {
        super(application);
        mListLiveData = mDataRepository.getListItemBean();
    }

    public void insertList(final int j) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<ListItemBean> list = new ArrayList<>();
                for (int i = 0; i < 20; i++) {
                    int x = i + j;
                    ListItemBean bean = new ListItemBean();
                    ListItemBean.UserInfo userInfo = new ListItemBean.UserInfo();
                    userInfo.age = x + "";
                    userInfo.username = "名称" + x;
                    bean.mUserBean = userInfo;
                    bean.content = "正文" + x;
                    bean.title = "biaoti" + x;
                    list.add(bean);
                }
                myDatabase.mListItemDao().onInserts(list);
            }
        }).start();
    }

    public void clear() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                myDatabase.mListItemDao().deleteAll();
            }
        }).start();
    }

    public LiveData<PagedList<ListItemBean>> getListLiveData() {
        return mListLiveData;
    }
}
