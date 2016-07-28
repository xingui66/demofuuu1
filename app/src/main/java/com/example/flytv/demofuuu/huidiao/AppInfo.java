package com.example.flytv.demofuuu.huidiao;

import java.util.ArrayList;

/**
 * 应用信息封装
 * @author Kevin
 * @date 2016-2-24
 */
public class AppInfo {

	public String id;
	public String name;
	public String packageName;
	public String iconUrl;
	public float stars;
	public long size;
	public String downloadUrl;
	public String des;

	//扩展字段, 用于展示应用详情
	public String author;
	public String date;
	public String downloadNum;
	public String version;
	public ArrayList<SafeInfo> safe;
	public ArrayList<String> screen;//截图信息

	public static class SafeInfo {
		public String safeDes;
		public String safeDesUrl;//安全描述的图片(打勾的图片)
		public String safeUrl;//安全绿色图标
	}

	//	 {
	//         "id": 1525490,
	//         "name": "有缘网",
	//         "packageName": "com.youyuan.yyhl",
	//         "iconUrl": "app/com.youyuan.yyhl/icon.jpg",
	//         "stars": 4,
	//         "size": 3876203,
	//         "downloadUrl": "app/com.youyuan.yyhl/com.youyuan.yyhl.apk",
	//         "des": "产品介绍：有缘是时下最受大众单身男女亲睐的婚恋交友软件。有缘网专注于通过轻松、"
	//     },
}
