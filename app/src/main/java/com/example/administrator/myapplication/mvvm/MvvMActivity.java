package com.example.administrator.myapplication.mvvm;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.databinding.ActivityMvvmBinding;
import com.example.administrator.myapplication.mvvm.bean.MyUser;
import com.example.administrator.myapplication.mvvm.method.MyMethod;


/**
 * Created by xcy on 2017/1/6 0006.
 */

public class MvvMActivity extends AppCompatActivity {
    MyUser user;
    ActivityMvvmBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm);
        user = new MyUser();
        user.setAge("12313");
        user.setName("你好");
        user.setAddress("asjdasdj");
        binding.setMuser(user);
//        binding.setNihao("哎呦我去");
        binding.setMymethod(new MyMethod());
        //


        binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse
//                        ("xl://goods:8888/goodsDetail?goodsId=10011002"));
                //yingteli://apppay/payDetail?orderNum=xxxxx&orderAmount=xxxx&remainAmount=xxxx
                // &payAmount=xxxx
//                startActivity(intent);
//                startActivity(new Intent(MainActivity.this, PayDemoActivity.class));
                Toast.makeText(MvvMActivity.this, "点击", Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        user.setAge("555555");
                    }
                }).start();
            }
        });
//        findViewById(R.id.btn2).setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                Toast.makeText(MvvMActivity.this, "长安了啊", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//
//        });
//        findViewById(R.id.btn2).setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//
//            }
//        });
    }


}
