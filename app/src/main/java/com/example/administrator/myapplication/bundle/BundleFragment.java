package com.example.administrator.myapplication.bundle;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myapplication.R;

/**
 * Created by xcy on 2017/5/5 0005.
 */

public class BundleFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle
            savedInstanceState) {
        Bundle bundle = getArguments();
        Log.e("asdasd", "");
        return inflater.inflate(R.layout.fragment_bundle, container, false);
    }
}
