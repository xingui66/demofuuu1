package com.example.flytv.demofuuu.huidiao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import android.content.Intent;
import android.net.Uri;


//MVC model(sqlite, sp, javabean), view(布局文件xml, 自定义控件), control(activity, service)
class DownloadManager {

    //下载相关的状态
    public static final int STATE_NONE = 0;//未下载
    public static final int STATE_WAITING = 1;//等待下载
    public static final int STATE_DOWNLOAD = 2;//正在下载
    public static final int STATE_PAUSE = 3;//下载暂停
    public static final int STATE_ERROR = 4;//下载失败
    public static final int STATE_SUCCESS = 5;//下载成功

    private static DownloadManager sInstance = new DownloadManager();

    //观察者集合
    private ArrayList<DownloadObserver> mObservers = new ArrayList<DownloadManager.DownloadObserver>();

    //下载对象的集合
    //private HashMap<String, DownloadInfo> mDownloadInfoMap = new HashMap<String, DownloadInfo>();
    //线程安全的hashmap
    private ConcurrentHashMap<String, DownLoadInfo> mDownloadInfoMap = new ConcurrentHashMap<String, DownLoadInfo>();

    //下载任务的集合
    private ConcurrentHashMap<String, DownloadTask> mDownloadTaskMap = new ConcurrentHashMap<String, DownloadTask>();

    private DownloadManager() {
    }

    public static DownloadManager getInstance() {
        return sInstance;
    }

    //2. 注册观察者
    public void registerObserver(DownloadObserver observer) {
        if (observer != null && !mObservers.contains(observer)) {
            mObservers.add(observer);
        }
    }

    //3. 注销观察者
    public void unregisterObserver(DownloadObserver observer) {
        if (observer != null && mObservers.contains(observer)) {
            mObservers.remove(observer);
        }
    }

    //4.通知所有观察者数据发生变化了
    public void notifyDownloadStateChanged(DownLoadInfo info) {
        for (DownloadObserver observer : mObservers) {
            observer.onDownloadStateChanged(info);
        }
    }

    //4.通知所有观察者数据发生变化了
    public void notifyDownloadProgressChanged(DownLoadInfo info) {
        for (DownloadObserver observer : mObservers) {
            observer.onDownloadProgressChanged(info);
        }
    }

    //开始下载
    public synchronized void download(AppInfo appInfo) {
        if (appInfo != null) {
            DownLoadInfo info = mDownloadInfoMap.get(appInfo.id);

            //如果对象为空, 说明以前没有下载过, 直接重新开始下载; 如果不为空,说明下载过, 此时继续接着原来的位置下载, 断点续传
            if (info == null) {
                info = DownLoadInfo.copy(appInfo);
            }

            info.currentState = STATE_WAITING;//等待下载
            //通知所有观察者, 下载状态已发生改变
            notifyDownloadStateChanged(info);

            //将下载对象保存在集合中
            mDownloadInfoMap.put(info.id, info);

            //开始下载
            DownloadTask task = new DownloadTask(info);
            //////////////////////////////////////////////////////ThreadManager.getTheadPool().execute(task);//放在线程池中异步执行
            mDownloadTaskMap.put(info.id, task);//将下载任务保存在集合中
        }
    }

    //下载任务
    public class DownloadTask implements Runnable {

        private DownLoadInfo info;

        public DownloadTask(DownLoadInfo info) {
            this.info = info;
        }

        @Override
        public void run() {
           /* //下载逻辑
            info.currentState = STATE_DOWNLOAD;
            notifyDownloadStateChanged(info);

            File file = new File(info.path);
            HttpResult httpResult = null;
            if (!file.exists() || file.length() != info.currentPos
                    || info.currentPos == 0) {
                //如果文件存在,但是文件有问题, 比如文件的长度和保存的更新位置不一致
                file.delete();//删除有问题的文件, 如果文件不存在, 此方法也不会出现异常, 没有任何作用
                info.currentPos = 0;//下载位置归零
                //从头开始下载
                httpResult = HttpHelper.download(HttpHelper.URL
                        + "download?name=" + info.downloadUrl);
            } else {//sdcard之前已经存在部分文件
                //断点续传
                httpResult = HttpHelper.download(HttpHelper.URL
                        + "download?name=" + info.downloadUrl + "&range="
                        + file.length());
            }

            InputStream in = null;
            FileOutputStream out = null;
            if (httpResult != null
                    && (in = httpResult.getInputStream()) != null) {

                try {
                    out = new FileOutputStream(file, true);//如果文件已存在,就接着原来的位置继续写

                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while ((len = in.read(buffer)) != -1
                            && info.currentState == STATE_DOWNLOAD) {//只有当前状态是正在下载才继续往下写
                        out.write(buffer, 0, len);
                        out.flush();//保证数据全部写入文件
                        info.currentPos += len;//更新当前下载位置
                        notifyDownloadProgressChanged(info);//下载进度发生变化, 通知所有观察者
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    IOUtils.close(out);
                    IOUtils.close(in);
                }

                //校验
                if (file.length() == info.size) {
                    //文件是完整的, 表示下载成功
                    info.currentState = STATE_SUCCESS;
                    notifyDownloadStateChanged(info);
                } else if (info.currentState == STATE_PAUSE) {
                    //中途暂停
                    notifyDownloadStateChanged(info);
                } else {
                    //下载失败
                    info.currentState = STATE_ERROR;
                    notifyDownloadStateChanged(info);

                    file.delete();//删除失败的文件
                    info.currentPos = 0;
                }

            } else {
                //下载失败
                info.currentState = STATE_ERROR;
                notifyDownloadStateChanged(info);

                file.delete();//删除失败的文件
                info.currentPos = 0;
            }

            //下载任务已经结束
            mDownloadTaskMap.remove(info.id);//从任务集合中移除对象*/
        }

    }

    //暂停下载
    //正在下载或等待下载时才可以暂停
    public synchronized void pause(AppInfo appInfo) {
        if (appInfo != null) {
            //获取当前的下载对象
            DownLoadInfo info = mDownloadInfoMap.get(appInfo.id);
            if (info != null) {
                if (info.currentState == STATE_DOWNLOAD
                        || info.currentState == STATE_WAITING) {
                    //暂停
                    //1.从线程池中移除当前的下载任务
                    DownloadTask task = mDownloadTaskMap.get(info.id);
                    if (task != null) {
                       ////////////////////////////////////////////////////////////// ThreadManager.getTheadPool().cancel(task);
                        //2.更新当前的下载状态,并通知观察者
                        info.currentState = STATE_PAUSE;
                        notifyDownloadStateChanged(info);
                    }
                }
            }
        }
    }

    //安装
    public synchronized void install(AppInfo appInfo) {
        if (appInfo != null) {
            DownLoadInfo info = mDownloadInfoMap.get(appInfo.id);

            if (info != null) {
                //跳转到系统安装页面
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setDataAndType(Uri.parse("file://" + info.path),
                        "application/vnd.android.package-archive");
                /////////////////////////////////////////////////////////////////////UIUtils.getContext().startActivity(intent);
            }
        }
    }

    /**
     * 1. 声明观察者接口
     */
    public interface DownloadObserver {
        //下载状态发生变化
        public void onDownloadStateChanged(DownLoadInfo info);

        //下载进度发生变化
        public void onDownloadProgressChanged(DownLoadInfo info);
    }

    //根据应用id来获取下载对象
    public DownLoadInfo getDownloadInfo(String id) {
        return mDownloadInfoMap.get(id);
    }
}
