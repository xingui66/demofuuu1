package com.example.flytv.demofuuu.maincontent;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.flytv.demofuuu.R;

public class MainContentActivity extends AppCompatActivity {

    private TabLayout tab;
    private ViewPager tab_viewpater;
    private TextView tv_content;
    private TabPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_content);

        //1.创建TabLayout
        tab=(TabLayout)findViewById(R.id.tab);
        tab_viewpater=(ViewPager) findViewById(R.id.tab_viewpater);
        tv_content=(TextView) findViewById(R.id.tv_content);

        iniTabData();

    }

    /**
     * 给TabLayout添加标签内容
     * 跟tabhost使用差不多
     */
    private void iniTabData() {
        //添加Tab
        tab.addTab(tab.newTab().setText("互动"));
        tab.addTab(tab.newTab().setText("书城"));
        tab.addTab(tab.newTab().setText("书架"));

        //点击事件的处理
        tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            //当Tab选中的时候调用
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab_viewpater.setCurrentItem(tab.getPosition());
            }

            //由选中变为非选中的时候调用
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            //同步点击时候回掉，，就是点击了下，又点击了下这个标签，重复选中时回掉
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        adapter=new TabPagerAdapter(getSupportFragmentManager());

        tab_viewpater.setAdapter(adapter);

        tab.setupWithViewPager(tab_viewpater);//关联

    }

}
