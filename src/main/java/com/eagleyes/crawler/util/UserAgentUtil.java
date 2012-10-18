/**
 * @文件名称：UserAgentUtil.java
 * @类路径：com.eagleyes.crawler.util
 * @版权:Copyright (c)2012
 * @作者：limeng
 * @时间：Oct 15, 20122:55:00 PM
 */
package com.eagleyes.crawler.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.math.RandomUtils;

/**
 * @描述：
 * @作者：limeng
 * @创建时间：Oct 15, 20122:55:00 PM
 */
public class UserAgentUtil {
    private static List<String> userAgents = new ArrayList<String>();
    private static int          size       = 0;
    static {
        userAgents
                .add("Mozilla/5.0 (X11; U; Linux x86_64; zh-CN; rv:1.9.2.17) Gecko/20110422 Ubuntu/10.04 (lucid) Firefox/3.6.17");
        userAgents
                .add("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.5; en-US; rv:1.9.0.10) Gecko/2009042315 Firefox/3.0.10");
        userAgents
                .add("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_5_7; en-us) AppleWebKit/525.28.3 (KHTML, like Gecko) Version/3.2.3 Safari/525.28.3");
        userAgents
                .add("Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.9) Gecko/2009042113 Ubuntu/8.10 (intrepid) Firefox/3.0.9");
        userAgents
                .add("Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; QQDownload 1.7; MAXTHON 2.0)");
        userAgents
                .add("Mozilla/5.0 (X11; U; Linux i686; zh-CN; rv:1.9.0.3) Gecko/2008101315 Ubuntu/8.10 (intrepid) Firefox/3.0.3");
        userAgents
                .add("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; .NET CLR 2.0.50727; MSN OptimizedIE8;ZHCN)");
        userAgents
                .add("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET4.0C)");
        userAgents
                .add("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; CIBA; .NET CLR 2.0.50727)");
        userAgents
                .add("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_5_7; en-us) AppleWebKit/528.18.1 (KHTML, like Gecko) Version/4.0 Safari/528.17");
        size = userAgents.size();
    }

    public static String getRandomUserAgent() {
        return userAgents.get(RandomUtils.nextInt(size));
    }
}
