package com.example.administrator.myapplication.archite.paging.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by xcy on 2017/12/8 0008.
 */

@Entity(tableName = "list_item")
public class ListItemBean {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String content;
    public String title;

    @Embedded
    public UserInfo mUserBean;

    public static class UserInfo {
        @ColumnInfo(name = "user_name")
        public String username;
        @ColumnInfo(name = "user_age")
        public String age;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }
}
