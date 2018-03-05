package com.example.administrator.myapplication.archite.paging.db;

import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by xcy on 2017/12/8 0008.
 */
@Dao
public interface ListItemDao {
    @Insert(onConflict = REPLACE)
    void onInserts(List<ListItemBean> list);

    @Query("SELECT * FROM list_item")
    DataSource.Factory<Integer, ListItemBean> findAllDS();

    @Query("DELETE FROM list_item")
    void deleteAll();
}
