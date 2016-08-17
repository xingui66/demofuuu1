package com.example.flytv.demofuuu;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.flytv.demofuuu.activity.FFragmentActivity;
import com.example.flytv.demofuuu.entity.example;
import com.example.flytv.demofuuu.login2main.FindItemsInteractorImpl;
import com.example.flytv.demofuuu.login2main.MainPresenter;
import com.example.flytv.demofuuu.login2main.MainPresenterImpl;
import com.example.flytv.demofuuu.login2main.MainView;
import com.example.flytv.demofuuu.maincontent.MainContentActivity;
import com.example.flytv.demofuuu.utils.OkHttpClientManager;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.greenrobot.event.EventBus;

/*import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;*/
//import com.example.flytv.demofuuu.huidiao.DownLoadManager;


public class MainActivity extends AppCompatActivity implements MainView, AdapterView.OnItemClickListener  {


    /**
     * 接口：http://web.sr.gehua.net.cn/md/api/home/0/index
     */
    private ListView listView;
    private ProgressBar progressBar;
    private MainPresenter presenter;

    private ViewPager mvp;
    private TextView tv_title;
    private LinearLayout ll_container;
    // private DownLoadManager mManager;

    private Button fragment_l_c,maincontent;
    private Context mContext;
    private ActionBarDrawerToggle mToggle;

    private int[] mImageIds = new int[]{R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private final String[] mImageDes = {"111111111111", "2222222222222222", "3333333333333333"};
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        EventBus.getDefault().register(this);

        /**
         * 有盟统计+
         */
        mContext = this;
        MobclickAgent.setDebugMode(true);
        // SDK在统计Fragment时，需要关闭Activity自带的页面统计，
        // 然后在每个页面中重新集成页面统计的代码(包括调用了 onResume 和 onPause 的Activity)。
        MobclickAgent.openActivityDurationTrack(false);
        // MobclickAgent.setAutoLocation(true);
        // MobclickAgent.setSessionContinueMillis(1000);
        MobclickAgent.startWithConfigure(
                new MobclickAgent.UMAnalyticsConfig(mContext, "578c384fe0f55a3973000562", "Umeng", MobclickAgent.EScenarioType.E_UM_NORMAL));
        MobclickAgent.setScenarioType(mContext, MobclickAgent.EScenarioType.E_UM_NORMAL);
        /**
         * 有盟统计-
         */

        initActionBar();

        fragment_l_c = (Button) findViewById(R.id.fragment_l_c);
        fragment_l_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, FFragmentActivity.class);
                startActivity(i);
            }
        });





        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        //viewpager开始
        tv_title = (TextView) findViewById(R.id.tv_title);
        mvp = (ViewPager) findViewById(R.id.vp);
        ll_container = (LinearLayout) findViewById(R.id.ll_container);

        //login开始
        listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(this);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        presenter = new MainPresenterImpl(this, new FindItemsInteractorImpl());

        //maincontent主界面的开始

        maincontent=(Button)findViewById(R.id.maincontent);
        maincontent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MainContentActivity.class);
                startActivity(i);
            }
        });




        ///////////////////
        mvp.setAdapter(new MyAdapter());
        //mvp.setCurrentItem(Integer.MAX_VALUE/2);
        mvp.setCurrentItem(mImageIds.length * 10000);//肯定是5的倍数

        //滑动的同时更新title
        mvp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            //页面滑动的时候
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                position = position % mImageIds.length;
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


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        //volley使用的方法
        volley_get();
        volley_post();

        //访问网络数据
        getDataFromServer();
    }

    //请求网络数据
    private void getDataFromServer() {
        OkHttpClientManager a=OkHttpClientManager.getInstance();
       /* a.getAsyn("http://web.sr.gehua.net.cn/md/api/home/0/index", new OkHttpClientManager.ResultCallback() {
            @Override
            public void onError(okhttp3.Request request, Exception e) {
                EventBus.getDefault().post("访问失败了！");
            }

            @Override
            public void onResponse(Object response) {
                EventBus.getDefault().post("成功了！");
            }
        });*/

    }
    protected void parseJson(String result){
        Gson gson=new Gson();
        example newsMenuBean = gson.fromJson(result, example.class);
        System.out.println("解析结果："+newsMenuBean);

    }

    private void initActionBar() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("我日");
        actionbar.setLogo(R.drawable.ssdk_logo);
        actionbar.setDisplayShowHomeEnabled(false);//隐藏图标
        actionbar.setDisplayHomeAsUpEnabled(true);   // 返回键，向上跳       ，微信是一个X号

        //初始化actionbar开关
        mToggle = new ActionBarDrawerToggle(this, drawer, R.string.isOpen, R.string.isClose);

        mToggle.syncState();//同步状态, 只有这个,actionbar和开关才能够关联起来
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.flytv.demofuuu/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        //volley,,与activity关联
        MyApplication.getHttpQueue().cancelAll("volley_get_remove");
        MyApplication.getHttpQueue().cancelAll("volley_post_remove");

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.flytv.demofuuu/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();

        // 界面销毁时，取消订阅16.
        EventBus.getDefault().unregister(this);
    }

    @Override public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        listView.setVisibility(View.INVISIBLE);
    }

    @Override public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        listView.setVisibility(View.VISIBLE);
    }

    @Override public void setItems(List<String> items) {
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items));
    }

    @Override public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        presenter.onItemClicked(position);
    }


    @Override protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
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
            return view == object;
        }

        //初始化view，类似于listview中的getView（）；
        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            Log.i("初始化item：", "" + position);

            position = position % mImageIds.length;

            //容器，viewpager，容器对象
            ImageView iv = new ImageView(getApplicationContext());
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
            container.removeView((View) object);
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
        /*int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }*/

        switch (item.getItemId()) {
            case android.R.id.home:
                Toast.makeText(this, "点击图标了", Toast.LENGTH_LONG).show();
                mToggle.onOptionsItemSelected(item);//如果当前抽屉打开, 则关闭, 反之亦然
                break;
            case R.id.action_settings:
                Toast.makeText(this, "点击action_settings", Toast.LENGTH_LONG).show();
                break;
            case R.id.action_settings1:
                Toast.makeText(this, "点击action_settings1", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onResume() {
        super.onResume();
        //统计页面
        MobclickAgent.onPageStart("MainActivity");

        MobclickAgent.onResume(this);

        presenter.onResume();
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("MainActivity");

        MobclickAgent.onPause(this);
    }


    //volley的使用
    public void volley_get() {
        //首先建立一个请求队列,,
        String url="http://115.28.108.179:8080/phone_service/LoginServlet?name=www&password=123456";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //请求成功
                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {//请求失败的回掉
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"网络请求失败！",Toast.LENGTH_LONG).show();
            }
        });

        //取消请求的时候用到
        request.setTag("volley_get_remove");

        MyApplication.getHttpQueue().add(request);
    }


    public void volley_post(){
        String url="http://115.28.108.179:8080/phone_service/LoginServlet";
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            //请求成功
                //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"网络请求失败！",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map=new HashMap<>();
                map.put("name","www");
                map.put("password","123456");
                return map;
            }
        };

        request.setTag("volley_post_remove");
        //将请求添加到队列里
        MyApplication.getHttpQueue().add(request);
    }


    //EventBus

    public void onEventMainThread(String ss) {
        Toast.makeText(this, ss, Toast.LENGTH_LONG).show();
    }
    public void onEventBackgroundThread(String ss) {
        Toast.makeText(this, ss, Toast.LENGTH_LONG).show();
    }
    public void onEventAsync(String ss) {
        Toast.makeText(this, ss, Toast.LENGTH_LONG).show();
    }
    public void onEvent(String ss) {
        Toast.makeText(this, ss, Toast.LENGTH_LONG).show();
    }
}
