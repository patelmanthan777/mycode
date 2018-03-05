package com.example.administrator.myapplication.archite.paging;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.administrator.myapplication.MyApplication;
import com.example.administrator.myapplication.archite.DataRepository;
import com.example.administrator.myapplication.archite.MyDatabase;

/**
 * Created by xcy on 2018/3/5 0005.
 */

public class BaseViewModel extends AndroidViewModel {
    protected DataRepository mDataRepository;
    protected final MyDatabase myDatabase;



    public BaseViewModel(@NonNull Application application) {
        super(application);
        mDataRepository= ((MyApplication) application).getRepository();
        myDatabase=((MyApplication) application).getDatabase();
    }

}
