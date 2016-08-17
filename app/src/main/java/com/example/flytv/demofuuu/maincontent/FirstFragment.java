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

import com.example.flytv.demofuuu.R;

import java.util.ArrayList;
import java.util.List;


public class FirstFragment extends Fragment {

    public FragmentActivity mActivity;
    private RecyclerView mRecyclerView;
    //private RecyclerView mRecyclerView1;
    private MyAdapter mMyAdapter;
    private List<String> mList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivity = getActivity();
        View view =inflater.inflate(R.layout.fragment_first,null);
        //initViews(view);初始化view

        //RecyclerView三部曲+LayoutManager
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        initData();
        mMyAdapter = new MyAdapter(mList);
        mRecyclerView.setAdapter(mMyAdapter);

        //为RecyclerView添加HeaderView和FooterView
        setHeaderView(mRecyclerView);
        setHeader1View(mRecyclerView);
        setFooterView(mRecyclerView);

        return view;
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (this.getView() != null)
            this.getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
    }

    //初始化RecyclerView中每个item的数据
    private void initData(){
        mList = new ArrayList<String>();
        for (int i = 0; i < 20; i++){
            mList.add("item" + i);
        }
    }

    private void setHeaderView(RecyclerView view){
        View header = LayoutInflater.from(mActivity).inflate(R.layout.header, view, false);
        mMyAdapter.setHeaderView(header);
    }
    private void setHeader1View(RecyclerView view){
        View header1 = LayoutInflater.from(mActivity).inflate(R.layout.header1, view, false);
        mMyAdapter.setHeader1View(header1);
    }

    private void setFooterView(RecyclerView view){
        View footer = LayoutInflater.from(mActivity).inflate(R.layout.footer, view, false);
        mMyAdapter.setFooterView(footer);
    }
}
