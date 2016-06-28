package com.example.flytv.demofuuu;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.flytv.demofuuu.R;

public class MainActivity extends AppCompatActivity {

    private ViewPager mvp;
    private TextView tv_title;
    private LinearLayout ll_container;

    private int [] mImageIds=new int [] {R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
    private final String [] mImageDes={"111111111111","2222222222222222","3333333333333333"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //viewpager开始
        tv_title=(TextView) findViewById(R.id.tv_title);
        mvp = (ViewPager) findViewById(R.id.vp);
        ll_container = (LinearLayout) findViewById(R.id.ll_container);


        mvp.setAdapter(new MyAdapter());
        //mvp.setCurrentItem(Integer.MAX_VALUE/2);
        mvp.setCurrentItem(mImageIds.length*10000);//肯定是5的倍数

        //滑动的同时更新title
        mvp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            //页面滑动的时候
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                position=position%mImageIds.length;
                tv_title.setText(mImageDes[position]);
            }

            //页面被选中的时候
            @Override
            public void onPageSelected(int position) {

            }

            //滑动状态发生变化
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            //return mImageIds.length;
            return Integer.MAX_VALUE;
        }

        //这个view是不是来源于Object
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        //初始化view，类似于listview中的getView（）；
        @Override
        public Object instantiateItem(ViewGroup container, int position){

            Log.i("初始化item：",""+position);

            position=position%mImageIds.length;

            //容器，viewpager，容器对象
            ImageView iv=new ImageView(getApplicationContext());
            //iv.setImageResource(mImageIds[position]);//按照图片的原始比例展示，可能有留白
            iv.setBackgroundResource(mImageIds[position]);//设置背景，图片会按照父控件的宽高进行展示
            //将布局添加给容器对象
            container.addView(iv);

            //返回布局对象
            return iv;
        }

        //销毁布局的,这里的object,是instantiateItem方法中返回的对象，object
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
