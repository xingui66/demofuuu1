package com.example.flytv.demofuuu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.flytv.demofuuu.R;
import com.example.flytv.demofuuu.eventbus.MsgEvent1;
import com.example.flytv.demofuuu.eventbus.MsgEvent2;

import de.greenrobot.event.EventBus;

/**
 * Created by Flytv on 2016/7/1.
 */
public class LeftFragment extends BaseFragment {
    private  ListView left_lv;
    private String[] strs = new String[]{"主线程消息1", "子线程消息1", "主线程消息2"};

    @Override
    public View initView() {
        View view =View.inflate(mActivity, R.layout.left_fragment,null);

        left_lv=(ListView)view.findViewById(R.id.left_fragment_lv);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        left_lv.setAdapter(new MyAdapter());
        left_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        // 主线程
                        System.out.println(
                                "----------------------主线程发的消息1"
                                        + " threadName: "+ Thread.currentThread().getName()
                                        + " threadId: " + Thread.currentThread().getId());
                        EventBus.getDefault().post(new MsgEvent1("主线程发的消息1"));
                        break;
                    case 1:
                        // 子线程
                        new Thread(){
                            public void run() {
                                System.out.println(
                                        "----------------------子线程发的消息1"
                                                + " threadName: "+ Thread.currentThread().getName()
                                                + " threadId: " + Thread.currentThread().getId());
                                EventBus.getDefault().post(new MsgEvent1("子线程发的消息1"));
                            };
                        }.start();

                        break;
                    case 2:
                        // 主线程
                        System.out.println(
                                "----------------------主线程发的消息2"
                                        + " threadName: "+ Thread.currentThread().getName()
                                        + " threadId: " + Thread.currentThread().getId());
                        EventBus.getDefault().post(new MsgEvent2("主线程发的消息2"));
                        break;
                }
            }
        });
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return strs.length;
        }

        @Override
        public Object getItem(int position) {
            return strs[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=View.inflate(mActivity, R.layout.list_item_left_menu, null);
            TextView tvTitle=(TextView) view.findViewById(R.id.tv_title);

            tvTitle.setText(strs[position]);

            return view;
        }
    }
}
