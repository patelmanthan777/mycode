package com.example.administrator.myapplication.archite.paging;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.arch.paging.PagedListAdapter;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myapplication.archite.UserBean;

/**
 * Created by xcy on 2018/3/2 0002.
 */

@Dao
interface UserDao {
    @Query("SELECT * FROM user ORDER BY lastName ASC")
    public abstract LivePagedListBuilder<Integer, UserBean> usersByLastName();

    @Query("SELECT * FROM user ORDER BY lastName ASC")
    public abstract DataSource.Factory<Integer, UserBean> usersByLastName123();
}

class MyViewModel extends ViewModel {
    public final LiveData<PagedList<UserBean>> usersList;

    public MyViewModel(UserDao userDao) {
//        usersList = new LivePagedListBuilder<>(
//                userDao.usersByLastName(), /* page size */ 20).build();
        usersList= userDao.usersByLastName().build();
    }
}

class MyActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        MyViewModel viewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        RecyclerView recyclerView = null;
//        recyclerView = findViewById(R.id.user_list);

        final UserAdapter adapter = new UserAdapter();

        viewModel.usersList.observe(this, new Observer<PagedList<UserBean>>() {
            @Override
            public void onChanged(@Nullable PagedList<UserBean> pagedList) {
                adapter.submitList(pagedList);
            }
        });

        recyclerView.setAdapter(adapter);
    }
}

class UserAdapter extends PagedListAdapter<UserBean, UserViewHolder> {
    public UserAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        UserBean user = getItem(position);
        if (user != null) {
//            holder.bindTo(user);
        } else {
            // Null defines a placeholder item - PagedListAdapter will automatically invalidate
            // this row when the actual object is loaded from the database
//            holder.clear();
        }
    }

    public static final DiffUtil.ItemCallback<UserBean> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<UserBean>() {
                @Override
                public boolean areItemsTheSame(@NonNull UserBean oldUser, @NonNull UserBean
                        newUser) {
                    // User properties may have changed if reloaded from the DB, but ID is fixed
                    return oldUser.getId().equals(newUser.getId());
                }

                @Override
                public boolean areContentsTheSame(@NonNull UserBean oldUser, @NonNull UserBean
                        newUser) {
                    // NOTE: if you use equals, your object must properly override Object#equals()
                    // Incorrectly returning false here will result in too many animations.
                    return oldUser.equals(newUser);
                }
            };
}

class UserViewHolder extends RecyclerView.ViewHolder {

    public UserViewHolder(View itemView) {
        super(itemView);
    }
}