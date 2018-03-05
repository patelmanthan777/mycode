package com.example.administrator.myapplication.archite;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import com.example.administrator.myapplication.MyApplication;

/**
 * Created by xcy on 2017/12/8 0008.
 */

public class UserInfoViewModel extends AndroidViewModel {
    private LiveData<UserBean> mUserBean;
    private DataRepository mDataRepository;
    private MutableLiveData<String> mName = new MutableLiveData<>();
    private LiveData<String> mEx;


    public UserInfoViewModel(@NonNull Application application) {
        super(application);
        mDataRepository = ((MyApplication) application).getRepository();
        mUserBean = mDataRepository.getUser();

//        mEx = Transformations.map(mUserBean, new Function<UserBean,
//                String>() {
//            @Override
//            public String apply(UserBean input) {
//                return input.getName();
//            }
//        });
        mEx = Transformations.map(mName, new Function<String,
                String>() {
            @Override
            public String apply(String input) {
                return input;
            }
        });
        mName.setValue("aiyou");
    }


    public LiveData<UserBean> getUserBean() {
        return mUserBean;
    }

    public LiveData<String> getEx() {
        return mEx;
    }

    public MutableLiveData<String> getName() {
        return mName;
    }
}
