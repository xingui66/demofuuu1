package com.example.flytv.demofuuu.maincontent.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.flytv.demofuuu.R;
import com.example.flytv.demofuuu.maincontent.holder.ListHolder;
import com.example.flytv.demofuuu.maincontent.holder.RecyHolder;
import com.example.flytv.demofuuu.maincontent.holder.RecycenterHolder;

import java.util.List;

/**
 * Created by Lizh on 2016/8/17.
 * 功能描述：
 */
public class RecyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    public static final int BTYPE_HEADER = 0;  //说明是带有Header的
    public static final int BTYPE_HEADER1 = 1;  //说明是带有Header1的
    public static final int BTYPE_FOOTER = 2;  //说明是带有Footer的
    public static final int BTYPE_NORMAL = 3;  //说明是不带有header和footer的
    private List<String> mDatas;
    private List<ImageView> mImageVeiwList;
    private View mHeaderView;
    private View mHeader1View;
    private View mFooterView;
    //HeaderView和FooterView的get和set函数
    public View getHeaderView() {
        return mHeaderView;
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }
    public View getHeader1View() {
        return mHeaderView;
    }

    public void setHeader1View(View header1View) {
        mHeader1View = header1View;
        notifyItemInserted(1);
    }

    public View getFooterView() {
        return mFooterView;
    }

    public void setFooterView(View footerView) {
        mFooterView = footerView;
        notifyItemInserted(getItemCount() - 1);
    }

    /**
     * 重写这个方法，很重要，是加入Header和Footer的关键，我们通过判断item的类型，从而绑定不同的view    *
     */
    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null && mFooterView == null) {
            return BTYPE_NORMAL;
        }
        if (position == 0) {
            //第一个item应该加载Header
            return BTYPE_HEADER;
        }
        if (position == 1) {
            //第二个item应该加载Header1
            return BTYPE_HEADER1;
        }
        if (position == getItemCount() - 1) {
            //最后一个,应该加载Footer
            return BTYPE_FOOTER;
        }
        return BTYPE_NORMAL;
    }

    //创建View，如果是HeaderView或者是FooterView，直接在Holder中返回
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == BTYPE_HEADER) {
            //return new ContentHolder();
            return new RecyHolder(mHeaderView);
        }
        if (mHeader1View != null && viewType == BTYPE_HEADER1) {
            return new RecycenterHolder(mHeader1View);
        }
        if (mFooterView != null && viewType == BTYPE_FOOTER) {
            return new ListHolder(mFooterView);
        }
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_item, parent, false);
        return new ListHolder(layout);

        /**
          Log.e("terry", "viewType = " + viewType);
         View view = null;
         if (viewType == COMMENT_FIRST) {
         view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_main_first, parent, false);
         return new CommentFirstHolder(view);
         } else if (viewType == COMMENT_SECOND) {
         view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_main_second, parent, false);
         return new CommentSecondHolder(view);
         }
         return null;
         */
    }

    //绑定View,   填充数据   ，这里是根据返回的这个position的类型，从而进行绑定的，   HeaderView和FooterView, 就不同绑定了
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BTYPE_NORMAL) {
            if (holder instanceof ListHolder) {
                //这里加载数据的时候要注意，是从position-2开始，因为position==0已经被header占用了,position==1已经被header1占用了
                ((ListHolder) holder).tv.setText(mDatas.get(position - 2));
                return;
            }
            return;
        } else if (getItemViewType(position) == BTYPE_HEADER) {
            if (holder instanceof RecyHolder) {
                //这里加载数据的时候要注意，是从position-2开始，因为position==0已经被header占用了,position==1已经被header1占用了
                //初始化三张图片Imageview，为对象，只有变成对象，才能操作对象
                ((RecyHolder) holder).vpcontent.setAdapter(new GuideAdapter());

                return;
            }
        } else if (getItemViewType(position) == BTYPE_HEADER1) {
            if (holder instanceof RecycenterHolder) {
                //这里加载数据的时候要注意，是从position-2开始，因为position==0已经被header占用了,position==1已经被header1占用了
                //初始化三张图片Imageview，为对象，只有变成对象，才能操作对象
                ((RecycenterHolder) holder).btn1.setText("左");
                ((RecycenterHolder) holder).btn2.setText("中");
                ((RecycenterHolder) holder).btn3.setText("右");

                return;
            }
        } else {
            return;
        }
    }
    //构造函数,并传参
    public RecyAdapter(List<String> list,List<ImageView> mImageVeiwList) {
        this.mDatas = list;
        this.mImageVeiwList = mImageVeiwList;
    }

    //填充viewpager数据
    class GuideAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mImageVeiwList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //初始化Item,这个item是imageView,
            //initData中，依然new了 3 个View，这里就不用new了，直接get,并将view放入container1容器
            ImageView view = mImageVeiwList.get(position);
            container.addView(view);
            return view;//返回view
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    }

    @Override
    public int getItemCount() {
        if (mHeaderView == null && mFooterView == null&& mHeader1View == null) {
            return mDatas.size();
        } else if (mHeaderView == null && mFooterView != null&& mHeader1View == null) {
            return mDatas.size() + 1;
        } else if (mHeaderView != null && mFooterView == null&& mHeader1View == null) {
            return mDatas.size() + 1;
        } else if(mHeaderView == null && mFooterView != null&& mHeader1View != null) {
            return mDatas.size() + 2;
        } else if (mHeaderView != null && mFooterView == null&& mHeader1View != null) {
            return mDatas.size() + 2;
        } else {
            return mDatas.size() + 3;
        }
    }
}
