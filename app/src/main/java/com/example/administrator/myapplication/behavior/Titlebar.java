package com.example.administrator.myapplication.behavior;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xcy on 2017/3/17 0017.
 */

public class Titlebar extends Toolbar {

    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.edittext)
    EditText mEdittext;

    public Titlebar(Context context) {
        this(context, null);
    }

    public Titlebar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Titlebar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.view_titlebar, this);
        ButterKnife.bind(this);
        initView();
    }


    public void initView() {

    }

    public void setSearch() {
        mEdittext.setVisibility(View.VISIBLE);
        mTitle.setVisibility(View.GONE);
    }

    public void setTitle() {
        mEdittext.setVisibility(View.GONE);
        mTitle.setVisibility(View.VISIBLE);
    }
}
