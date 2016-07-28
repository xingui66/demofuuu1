package com.example.flytv.demofuuu.huidiao;

import java.io.File;


import android.os.Environment;

public class DownLoadInfo {

    public String id;
    public String name;
    public long size;
    public String downloadUrl;

    public long currentPos;//当前下载的位置
    public int currentState;//当前下载状态
    public String path;//下载路径 sdk/googlemarket81/download/xxx.apk

    //获取当前的下载进度
    public float getProgress() {
        if (size == 0) {
            return 0;
        }

        return (float) currentPos / size;
    }

    //根据AppInfo创建一个DownloadInfo对象
    public static DownLoadInfo copy(AppInfo appInfo) {
        DownLoadInfo info = new DownLoadInfo();
        info.id = appInfo.id;
        info.name = appInfo.name;
        info.downloadUrl = appInfo.downloadUrl;
        info.size = appInfo.size;
        info.currentPos = 0;
        info.currentState = DownloadManager.STATE_NONE;//未下载
        info.path = info.getFilePath();

        return info;
    }

    //获取文件下载路径
    public String getFilePath() {
        //文件夹路径
        String dirPath = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/googlemarket81/download";

        if (createDir(dirPath)) {//文件夹已存在或创建文件夹成功
            String path = dirPath + "/" + name + ".apk";
            return path;
        }

        return null;
    }

    //注意权限:WRITE_EXTERNAL_STORAGE
    private boolean createDir(String dirPath) {
        File dir = new File(dirPath);
        if (!dir.exists() || !dir.isDirectory()) {
            return dir.mkdirs();
        }

        return true;
    }

}
