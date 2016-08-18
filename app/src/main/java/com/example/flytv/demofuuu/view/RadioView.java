package com.example.flytv.demofuuu.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;

import com.example.flytv.demofuuu.R;

/**
 * Created by Lizh on 2016/8/17.
 * 功能描述：
 */
public class RadioView extends RadioButton{
    //图片大小
    //private int drawableSize;

    public RadioView(Context context) {
        this(context,null);
    }

    public RadioView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadioView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.RadioView);
        //drawableSize = a.getDimensionPixelSize(R.styleable.MyRadioButton_rbDrawableTopSize, 50);
        Drawable drawableTop = a.getDrawable(R.styleable.RadioView_rbDrawableTop);

        //释放资源
        a.recycle();

        setCompoundDrawablesWithIntrinsicBounds(null,drawableTop,null,null);
    }

    @Override
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable left, Drawable top, Drawable right, Drawable bottom) {
        super.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
        if(top != null){
            //这里只要改后面两个参数就好了，一个宽一个是高，如果想知道为什么可以查找源码
            top.setBounds(0,0,70,60);
        }
        setCompoundDrawables(left,top,right,bottom);
    }
}