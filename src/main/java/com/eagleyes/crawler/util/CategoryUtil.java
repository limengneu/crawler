/**
 * @文件名称：CategoryUtil.java
 * @类路径：com.tzc.biz.util
 * @版权:Copyright (c)2012
 * @作者：limeng
 * @时间：Oct 16, 20123:19:47 PM
 */
package com.eagleyes.crawler.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @描述：
 * @作者：limeng
 * @创建时间：Oct 16, 20123:19:47 PM
 */
public class CategoryUtil {
	private static Map<String, String> categoryMap = new HashMap<String, String>();
	static {
		categoryMap.put("油罐车系列", "ygc");
		categoryMap.put("洒水车系列", "ssc");
		categoryMap.put("散装水泥车系列", "szsnc");
		categoryMap.put("吸粪车系列", "xfc");
		categoryMap.put("吸污车系列", "xwc");
		categoryMap.put("垃圾车系列", "ljc");
		categoryMap.put("混凝土搅拌车系列", "hltjbc");
		categoryMap.put("随车起重运输车系列", "scqzc");
		categoryMap.put("随车起重运输车", "scqzc");
		categoryMap.put("高压清洗车系列", "gyqxc");
		categoryMap.put("高压清洗车", "gyqxc");
		categoryMap.put("高空作业车系列", "gkzyc");
		categoryMap.put("高空作业车", "gkzyc");
		categoryMap.put("清障车系列", "qzc");
		categoryMap.put("防暴车系列", "fbc");
		categoryMap.put("扫路车系列", "slc");
		categoryMap.put("半挂车系列", "bgc");
		categoryMap.put("教练车系列", "jlc");
		categoryMap.put("化工液体运输车", "hgytysc");
		categoryMap.put("粉粒物料运输车", "flwlysc");
		categoryMap.put("自卸车系列", "zxc");
		categoryMap.put("平板运输车", "pbysc");
		categoryMap.put("厢式运输车", "xsysc");
		categoryMap.put("仓栅式运输车", "csysc");
		categoryMap.put("消防车系列", "xfc");
		categoryMap.put("冷藏车系列", "ncc");
		categoryMap.put("专用车配件", "zyc");
		categoryMap.put("液化气储罐系列", "ycgc");
		categoryMap.put("搅拌车系列", "jbc");
		categoryMap.put("防爆车系列", "fbc");
	}

	public static String getCategoryValue(String key) {
		return categoryMap.get(key);
	}

}
