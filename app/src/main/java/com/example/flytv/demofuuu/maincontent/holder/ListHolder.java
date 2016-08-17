package com.example.flytv.demofuuu.maincontent.holder;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.flytv.demofuuu.R;

/**
 * Created by Lizh on 2016/8/17.
 * 功能描述：
 */
public class ListHolder extends RecyclerView.ViewHolder {
    public TextView tv;
    public ViewPager vpcontent;
    private View mHeaderView;
    private View mHeader1View;
    private View mFooterView;




    public ListHolder(View itemView) {
        super(itemView);

        //如果是headerview或者是footerview,直接返回
        if (itemView != mHeaderView) {
            //return;
            vpcontent = (ViewPager) itemView.findViewById(R.id.content_viewpager);

        }
        if (itemView == mHeader1View) {
            return;
        }
        if (itemView == mFooterView) {
            return;
        }
        tv = (TextView) itemView.findViewById(R.id.item);
    }



}