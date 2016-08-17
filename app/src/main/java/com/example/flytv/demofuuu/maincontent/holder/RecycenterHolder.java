package com.example.flytv.demofuuu.maincontent.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.flytv.demofuuu.R;

/**
 * Created by Lizh on 2016/8/17.
 * 功能描述：
 */
public class RecycenterHolder extends RecyclerView.ViewHolder{
    public Button btn1,btn2, btn3;
    private View mHeaderView;

    public RecycenterHolder(View itemView) {
        super(itemView);
        if (itemView != mHeaderView) {
            btn1 = (Button) itemView.findViewById(R.id.zuo);
            btn2 = (Button) itemView.findViewById(R.id.zhong);
            btn3 = (Button) itemView.findViewById(R.id.you);
        }
        //tv = (TextView) itemView.findViewById(R.id.item);

    }
}
