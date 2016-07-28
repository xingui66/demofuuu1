package com.example.flytv.demofuuu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.flytv.demofuuu.R;
import com.example.flytv.demofuuu.eventbus.MsgEvent1;
import com.example.flytv.demofuuu.eventbus.MsgEvent2;

import de.greenrobot.event.EventBus;

/**
 * Created by Flytv on 2016/7/1.
 */
public class ContentFragment extends BaseFragment {

    private TextView content_fragment_tv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 界面创建时，订阅事件， 接受消息
        EventBus.getDefault().register(this);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        // 界面销毁时，取消订阅
        EventBus.getDefault().unregister(this);
    }
    @Override
    public View initView() {
        View view =View.inflate(mActivity, R.layout.content_fragment,null);
        content_fragment_tv=(TextView)view.findViewById(R.id.content_fragment_tv);
        return view;
    }

    /**
     * 与发布者在同一个线程
     * @param msg 事件1
     */
    public void onEvent(MsgEvent1 msg){
        String content = msg.getMsg()
                + "\n ThreadName: " + Thread.currentThread().getName()
                + "\n ThreadId: " + Thread.currentThread().getId();
        System.out.println("onEvent(MsgEvent1 msg)收到" + content);
    }

    /**
     * 执行在主线程。
     * 非常实用，可以在这里将子线程加载到的数据直接设置到界面中。
     * @param msg 事件1
     */
    public void onEventMainThread(MsgEvent1 msg){
        String content = msg.getMsg()
                + "\n ThreadName: " + Thread.currentThread().getName()
                + "\n ThreadId: " + Thread.currentThread().getId();
        System.out.println("onEventMainThread(MsgEvent1 msg)收到" + content);
        content_fragment_tv.setText(content);
    }

    /**
     * 执行在子线程，如果发布者是子线程则直接执行，如果发布者不是子线程，则创建一个再执行
     * 此处可能会有线程阻塞问题。
     * @param msg 事件1
     */
    public void onEventBackgroundThread(MsgEvent1 msg){
        String content = msg.getMsg()
                + "\n ThreadName: " + Thread.currentThread().getName()
                + "\n ThreadId: " + Thread.currentThread().getId();
        System.out.println("onEventBackgroundThread(MsgEvent1 msg)收到" + content);
    }

    /**
     * 执行在在一个新的子线程
     * 适用于多个线程任务处理， 内部有线程池管理。
     * @param msg 事件1
     */
    public void onEventAsync(MsgEvent1 msg){
        String content = msg.getMsg()
                + "\n ThreadName: " + Thread.currentThread().getName()
                + "\n ThreadId: " + Thread.currentThread().getId();
        System.out.println("onEventAsync(MsgEvent1 msg)收到" + content);
    }

    /**
     * 与发布者在同一个线程
     * @param msg 事件2
     */
    public void onEvent(MsgEvent2 msg){
        String content = msg.getMsg()
                + "\n ThreadName: " + Thread.currentThread().getName()
                + "\n ThreadId: " + Thread.currentThread().getId();
        System.out.println("onEvent(MsgEvent2 msg)收到" + content);
        content_fragment_tv.setText(content);
    }
}
