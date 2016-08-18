package com.example.flytv.demofuuu.maincontent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.flytv.demofuuu.MyApplication;
import com.example.flytv.demofuuu.R;
import com.example.flytv.demofuuu.maincontent.adapter.RecyAdapter;

import java.util.ArrayList;
import java.util.List;


public class BlankFragment extends Fragment {
    public FragmentActivity mActivity;
    private RecyclerView mOne_recyiew;
    private RecyAdapter mRecyAdater;
    private List<String> mList;

    private ArrayList<ImageView> mImageVeiwList;
    private int[] mImageIds = new int[] { R.drawable.ic_action_accept,
            R.drawable.ic_action_accept, R.drawable.ic_action_accept };



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivity = getActivity();
        View view =inflater.inflate(R.layout.fragment_blank,null);
        //initViews(view);初始化view
        mOne_recyiew = (RecyclerView)view.findViewById(R.id.one_recyiew);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        mOne_recyiew.setLayoutManager(linearLayoutManager);
        initData();
        mRecyAdater = new RecyAdapter(mList,mImageVeiwList);
        mOne_recyiew.setAdapter(mRecyAdater);

        //为RecyclerView添加HeaderView和FooterView
        setHeaderView(mOne_recyiew);
        setHeader1View(mOne_recyiew);
        setFooterView(mOne_recyiew);

        return view;
    }

    private void setHeaderView(RecyclerView view){
        View header = LayoutInflater.from(mActivity).inflate(R.layout.header, view, false);
        mRecyAdater.setHeaderView(header);
    }
    private void setHeader1View(RecyclerView view){
        View header1 = LayoutInflater.from(mActivity).inflate(R.layout.header1, view, false);
        mRecyAdater.setHeader1View(header1);
    }

    private void setFooterView(RecyclerView view){
        View footer = LayoutInflater.from(mActivity).inflate(R.layout.footer, view, false);
        mRecyAdater.setFooterView(footer);
    }

    //初始化RecyclerView中每个item的数据
    private void initData(){
        mList = new ArrayList<String>();
        for (int i = 0; i < 20; i++){
            mList.add("item" + i);
        }

        mImageVeiwList = new ArrayList<ImageView>();
        //初始化3张图片，和 3 个相应的View
        for(int i=0;i<mImageIds.length;i++) {
            ImageView view = new ImageView(MyApplication.getContext());
            view.setBackgroundResource(mImageIds[i]);
            mImageVeiwList.add(view);

        }
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (this.getView() != null)
            this.getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
    }
}
