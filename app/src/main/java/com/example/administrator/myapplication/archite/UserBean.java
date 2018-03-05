package com.example.administrator.myapplication.archite;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by xcy on 2017/12/8 0008.
 */

@Entity(tableName = "user")
public class UserBean {
    @NonNull
    @PrimaryKey
    private String id;
    private String name;
    private String age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
