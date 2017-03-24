package com.example.administrator.myapplication.courese_detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xcy on 2017/3/20 0020.
 */

public class TestFragment1 extends Fragment {
    @BindView(R.id.text)
    TextView mText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_test1, container);
        ButterKnife.bind(this, view);
        return view;
    }
}
