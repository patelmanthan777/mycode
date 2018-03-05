package com.example.administrator.myapplication.archite;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.archite.lifecicle.LifeTestActivity;

/**
 * Created by xcy on 2017/12/8 0008.
 * 共享
 */

public class UserCompareFragment extends Fragment {
    private UserInfoViewModel viewModel;
    TextView mTv, mTvId, mTvEx;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_userinfo_1, container, false);
        mTv = view.findViewById(R.id.tv_name);
        mTvId = view.findViewById(R.id.tv_id);
        mTvEx = view.findViewById(R.id.tv_ex);
        view.findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                viewModel.getUserBean().getValue().setName("asda");
                viewModel.getName().setValue("xasdasd");
            }
        });
        view.findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), LifeTestActivity.class));
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(UserInfoViewModel.class);
        viewModel.getUserBean().observe(this, new Observer<UserBean>() {
            @Override
            public void onChanged(@Nullable UserBean userBean) {
//                if (userBean != null) {
//                    mTv.setText(userBean.getName());
//                    mTvId.setText(userBean.getAge());
//                    mTvEx.setText(viewModel.getEx().getValue());
//                }
            }
        });
        viewModel.getName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if (!TextUtils.isEmpty(s)) {
                    mTvId.setText(s);
                }
            }
        });
        viewModel.getEx().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if (!TextUtils.isEmpty(s)) {
                    mTvEx.setText(viewModel.getEx().getValue());
                }
            }
        });
    }

}
