package com.example.flytv.demofuuu.entity;

import java.util.ArrayList;

/**
 * Created by Flytv on 2016/8/2.
 */
public class example {
    //成员变量
    //给成员变量的数组类型的加个集合类型
    //给集合类型里的对象，命名一个，并创建相应的类

    public String message;
    public boolean success;
    public ResultExample result;
    public class ResultExample{
        public ArrayList<Banner> banner;
        //public ArrayList<Competition> competition;
        //public ArrayList<News> news;
        //·····
        public class Banner{
            public String id;
            public String title;
            public String content;
            public String author;
            public String pic;
            public ArrayList<BannerFiles> files;
            public String url;

            public class BannerFiles{
                public String name;
                public String path;
            }
        }

    }

    @Override
    public String toString() {
        return "example{" +
                "message='" + message + '\'' +
                ", success=" + success +
                ", result=" + result +
                '}';
    }
}
