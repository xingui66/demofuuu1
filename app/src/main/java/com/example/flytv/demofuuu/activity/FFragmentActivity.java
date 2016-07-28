package com.example.flytv.demofuuu.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.flytv.demofuuu.R;
import com.example.flytv.demofuuu.fragment.ContentFragment;
import com.example.flytv.demofuuu.fragment.LeftFragment;
import com.example.flytv.demofuuu.view.klokview;

public class FFragmentActivity extends AppCompatActivity {
    private static final String TAG_LEFT_MENU = "TAG_LEFT_MENU";
    private static final String TAG_CONTENT = "TAG_CONTENT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ffragment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Button jianting = (Button) findViewById(R.id.jianting);
        klokview klokview1=(klokview)findViewById(R.id.klokview1);
        final TextView tv_jianting=(TextView)findViewById(R.id.tv_jianting);
        setSupportActionBar(toolbar);


        //fragment
        initFragment();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        jianting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackgroundColor(Color.red(250));
            }
        });

        klokview1.addKlokviewListener(new klokview.KlokviewListener() {
            @Override
            public void onchange() {

            }

            @Override
            public void onchange(View v) {

            }

            @Override
            public void onchange(int i) {
                //tv_jianting.setText(i+"");
            }
        });
    }

    //初始化fragment
    private void initFragment() {
        //获取fragment管理器
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();//开始事务
        transaction.replace(R.id.left_fragment, new LeftFragment(),
                TAG_LEFT_MENU);//替换fragment,参1:待替换的根布局id; 参2:要替换的fragment;参3:打标记
        transaction
                .replace(R.id.content_fragment, new ContentFragment(), TAG_CONTENT);//替换fragment,参1:待替换的根布局id; 参2:要替换的fragment;参3:打标记
        transaction.commit();//提交事务
        //根据标记找到对应的fragment
        //fm.findFragmentByTag(TAG_LEFT_MENU);
    }
    //获取侧边栏fragment
    public LeftFragment getLeftMenuFragment() {
        FragmentManager fm = getSupportFragmentManager();
        LeftFragment fragment = (LeftFragment) fm
                .findFragmentByTag(TAG_LEFT_MENU);
        return fragment;
    }
    //获取主页面fragment
    public ContentFragment getContentFragment() {
        FragmentManager fm = getSupportFragmentManager();
        ContentFragment fragment = (ContentFragment) fm
                .findFragmentByTag(TAG_CONTENT);
        return fragment;
    }

}
