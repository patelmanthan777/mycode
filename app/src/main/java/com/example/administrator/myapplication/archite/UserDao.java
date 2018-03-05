package com.example.administrator.myapplication.archite;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by xcy on 2017/12/8 0008.
 */
@Dao
public interface UserDao {
    @Insert(onConflict = REPLACE)
    void save(UserBean userBean);

    @Query("SELECT * FROM user WHERE id = :userId")
    LiveData<UserBean> load(String userId);
}
