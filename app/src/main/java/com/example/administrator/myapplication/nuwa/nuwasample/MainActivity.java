package com.example.administrator.myapplication.nuwa.nuwasample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.nuwa.nuwasample.Hello.Hello;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuwa);
        TextView textView = (TextView) findViewById(R.id.textview);
        textView.setText(new Hello().say());
    }

}
