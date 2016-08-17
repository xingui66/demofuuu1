package com.example.flytv.demofuuu.maincontent.holder;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.flytv.demofuuu.R;

/**
 * Created by Lizh on 2016/8/17.
 * 功能描述：
 */
public class RecyHolder extends RecyclerView.ViewHolder{
    public ViewPager vpcontent;
    private View mHeaderView;

    public RecyHolder(View itemView) {
        super(itemView);
        if (itemView != mHeaderView) {
            vpcontent = (ViewPager) itemView.findViewById(R.id.content_viewpager);
        }
        //tv = (TextView) itemView.findViewById(R.id.item);

    }
}
