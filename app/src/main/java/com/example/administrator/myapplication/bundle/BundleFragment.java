package com.example.administrator.myapplication.bundle;

import android.animation.Animator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.example.administrator.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xcy on 2017/5/5 0005.
 */

public class BundleFragment extends Fragment {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle
            savedInstanceState) {
        Bundle bundle = getArguments();
        Log.e("asdasd", "");
        View view = inflater.inflate(R.layout.fragment_bundle, container, false);
        unbinder = ButterKnife.bind(this, view);
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        mRecyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 20);

        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager);
        mRecyclerView.setAdapter(delegateAdapter);
        return view;
    }

    @Override
    public Animator onCreateAnimator(int transit, boolean enter, int nextAnim) {
        Log.e("asdasd", "onCreateAnimator");
        return super.onCreateAnimator(transit, enter, nextAnim);
    }

    @Override
    public void onStart() {
        Log.e("asdasd", "onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.e("asdasd", "onResume");
        super.onResume();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.e("asdasd", "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
