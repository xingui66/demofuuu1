package com.example.flytv.demofuuu.maincontent;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.flytv.demofuuu.MyApplication;
import com.example.flytv.demofuuu.R;
import com.example.flytv.demofuuu.maincontent.holder.ListHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lizh on 2016/8/16.
 * 功能描述：
 */
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_HEADER = 0;  //说明是带有Header的
    public static final int TYPE_HEADER1 = 1;  //说明是带有Header1的
    public static final int TYPE_FOOTER = 2;  //说明是带有Footer的
    public static final int TYPE_NORMAL = 3;  //说明是不带有header和footer的

    //获取从Activity中传递过来每个item的数据集合
    private List<String> mDatas;
    //HeaderView, FooterView
    private View mHeaderView;
    private View mHeader1View;
    private View mFooterView;
    private int[] mImageIds = new int[] { R.drawable.ic_action_accept,
            R.drawable.ic_action_accept, R.drawable.ic_action_accept };
    private ViewPager mViewPager;
    private ArrayList<ImageView> mImageVeiwList;

    //构造函数
    public MyAdapter(List<String> list) {
        this.mDatas = list;
    }

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
            return TYPE_NORMAL;
        }
        if (position == 0) {
            //第一个item应该加载Header
            return TYPE_HEADER;
        }
        if (position == 1) {
            //第二个item应该加载Header1
            return TYPE_HEADER1;
        }
        if (position == getItemCount() - 1) {
            //最后一个,应该加载Footer
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL;
    }

    //创建View，如果是HeaderView或者是FooterView，直接在Holder中返回
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) {
            //return new ContentHolder();
            return new ListHolder(mHeaderView);
        }
        if (mHeader1View != null && viewType == TYPE_HEADER1) {
            return new ListHolder(mHeader1View);
        }
        if (mFooterView != null && viewType == TYPE_FOOTER) {
            return new ListHolder(mFooterView);
        }
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_item, parent, false);
        return new ListHolder(layout);
    }

    //绑定View，这里是根据返回的这个position的类型，从而进行绑定的，   HeaderView和FooterView, 就不同绑定了
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_NORMAL) {
            if (holder instanceof ListHolder) {
                //这里加载数据的时候要注意，是从position-2开始，因为position==0已经被header占用了,position==1已经被header1占用了
                ((ListHolder) holder).tv.setText(mDatas.get(position - 2));
                return;
            }
            return;
        } else if (getItemViewType(position) == TYPE_HEADER) {
            if (holder instanceof ListHolder) {
                //这里加载数据的时候要注意，是从position-2开始，因为position==0已经被header占用了,position==1已经被header1占用了
                //初始化三张图片Imageview，为对象，只有变成对象，才能操作对象
                mImageVeiwList = new ArrayList<ImageView>();
                //初始化3张图片，和 3 个相应的View
                for(int i=0;i<mImageIds.length;i++){
                    ImageView view=new ImageView(MyApplication.getContext());
                    view.setBackgroundResource(mImageIds[i]);
                    mImageVeiwList.add(view);

               /* ''''''//初始化小圆点
                ImageView point = new ImageView(this);
                point.setImageResource(R.drawable.shape_point_default);
                llContainer.addView(point);//添加给容器*/
                }

                ((ListHolder) holder).vpcontent.setAdapter(new GuideAdapter());

                return;
            }
        } else if (getItemViewType(position) == TYPE_HEADER1) {
            return;
        } else {
            return;
        }
    }

    //在这里面加载ListView中的每个item的布局
    /*class ListHolder1 extends RecyclerView.ViewHolder {
        TextView tv;
        ViewPager vpcontent;
        public ListHolder(View itemView) {
            super(itemView);

            //如果是headerview或者是footerview,直接返回
            if (itemView == mHeaderView) {
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
    }*/

    //返回View中Item的个数，这个时候，总的个数应该是ListView中Item的个数加上HeaderView和FooterView
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

    //填充viewpager数据
    class GuideAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mImageIds.length;
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
}
