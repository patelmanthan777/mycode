package com.example.administrator.myapplication.archite.paging;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.archite.paging.db.ListItemBean;

/**
 * Created by xcy on 2018/3/5 0005.
 */

public class MyListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private ListItemViewModel mListItemViewModel;
    private MyRecycleAdapter mMyRecycleAdapter;
    private static int i = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_list, container, false);
        mRecyclerView = view.findViewById(R.id.list);
        view.findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        view.findViewById(R.id.btn_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListItemViewModel.insertList(++i);
            }
        });
        view.findViewById(R.id.btn_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListItemViewModel.clear();
            }
        });
        mMyRecycleAdapter = new MyRecycleAdapter(new DiffUtil.ItemCallback<ListItemBean>() {
            @Override
            public boolean areItemsTheSame(ListItemBean oldItem, ListItemBean newItem) {
                return oldItem.id == newItem.id;
            }

            @Override
            public boolean areContentsTheSame(ListItemBean oldItem, ListItemBean newItem) {
                return oldItem.id == newItem.id;
            }
        });
        mRecyclerView.setAdapter(mMyRecycleAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        mListItemViewModel = ViewModelProviders.of(getActivity()).get(ListItemViewModel.class);
        mListItemViewModel.getListLiveData().observe(this, new Observer<PagedList<ListItemBean>>() {
            @Override
            public void onChanged(@Nullable PagedList<ListItemBean> listItemBeans) {
                Log.e("asdasd", "onChanged" + (listItemBeans == null));
                if (listItemBeans != null) {
                    mMyRecycleAdapter.submitList(listItemBeans);
                }
            }
        });
        super.onActivityCreated(savedInstanceState);
    }
}
