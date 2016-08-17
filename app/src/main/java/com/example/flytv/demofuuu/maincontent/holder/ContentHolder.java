package com.example.flytv.demofuuu.maincontent.holder;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.flytv.demofuuu.MyApplication;
import com.example.flytv.demofuuu.R;
import com.example.flytv.demofuuu.entity.ContentInfo;

/**
 * Created by Lizh on 2016/8/17.
 * 功能描述：
 */
public class ContentHolder extends BaseHolder<ContentInfo> {

    private ViewPager content_viewpager;

    public ContentHolder(View v) {
        super(v);
    }

    @Override
    public View initView() {
        View view = View.inflate(MyApplication.getContext(),R.layout.header,null);
        content_viewpager = (ViewPager) view.findViewById(R.id.content_viewpager);

        return view;
    }

    @Override
    public void refreshView(ContentInfo data) {

    }

}
