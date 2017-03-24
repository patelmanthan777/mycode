package com.example.administrator.myapplication.courese_detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.administrator.myapplication.R.id.viewpager;

/**
 * Created by xcy on 2017/3/20 0020.
 */

public class CourseDetailActivity extends AppCompatActivity {
    @BindView(viewpager)
    ViewPager mViewpager;
    //    @BindView(R.id.tabs)
//    TabLayout mTabs;
    private List<Fragment> mFragmentList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        ButterKnife.bind(this);
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new TestFragment());
        mFragmentList.add(new TestFragment1());
        mFragmentList.add(new TestFragment2());
        MFramentAdapter adapter = new MFramentAdapter(getSupportFragmentManager());
        mViewpager.setOffscreenPageLimit(3);
        mViewpager.setAdapter(adapter);
//        mTabs.setupWithViewPager(mViewpager);
//        mTabs.addTab(mTabs.newTab().setText("商品"));
//        mTabs.addTab(mTabs.newTab().setText("详情"));
//        mTabs.addTab(mTabs.newTab().setText("评价"));

    }

    private class MFramentAdapter extends FragmentPagerAdapter {
        String[] titles = new String[]{"商品", "详情", "评价"};
        Fragment[] fragments = new Fragment[]{new TestFragment(),
                new TestFragment(), new TestFragment()};

        public MFramentAdapter(FragmentManager fm) {
            super(fm);

        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }
    }
}
