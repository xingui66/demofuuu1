package com.example.flytv.demofuuu.maincontent.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Lizh on 2016/8/17.
 * 功能描述：是一个静态类,作用就在于减少不必要的调用findViewById，
 * 然后把对底下的控件引用存在ViewHolder里面，再在View.setTag(holder)把它放在view里，下次就可以直接取了。
 */
public abstract class BaseHolder<T> extends RecyclerView.ViewHolder {

    //1. 初始化item布局
    //2. findViewbyId初始化各个控件
    //3. 设置tag
    //4. 根据数据刷新item的界面

    private View mRootView;//item的根布局对象
    private T data;

    public BaseHolder(View v) {
        super(v);
        mRootView = initView();
        //3. 设置tag
        mRootView.setTag(this);
    }

    //1. 初始化item布局
    //2. findViewbyId初始化各个控件
    public abstract View initView();

    //4. 根据数据刷新item的界面
    public abstract void refreshView(T data);

    //设置数据并刷新界面
    public void setData(T data) {
        this.data = data;
        refreshView(data);
    }

    //获取当前item的数据
    public T getData() {
        return data;
    }

    //获取item的根布局对象
    public View getRootView() {
        return mRootView;
    }

}
