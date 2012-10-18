/**
 * @文件名称：FetcherConfig.java
 * @类路径：com.eagleyes.crawler.fetcher
 * @版权:Copyright (c)2012
 * @作者：limeng
 * @时间：Oct 15, 20122:53:19 PM
 */
package com.eagleyes.crawler.fetcher;

import java.util.HashMap;
import java.util.Map;

/**
 * @描述：
 * @作者：limeng
 * @创建时间：Oct 15, 20122:53:19 PM
 */
public class FetcherConfig {
	public static final String  REQUESTMETHOD_GET  = "GET";
    public static final String  REQUESTMETHOD_POST = "POST";

    private String              ip;
    private String              url;
    //使用GET还是POST提交HTTP请求，默认使用GET
    private String              requestMethod;
    //请求超时时间，单位为秒，如果不设置则默认为10秒
    private int                 requestTimeout;
    private Map<String, String> headers            = new HashMap<String, String>();
    //如果headers中已经设置了cookie，那么再设置cookies属性会覆盖掉headers中的cookie
    private String              cookies;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getRequestTimeout() {
        return requestTimeout;
    }

    public void setRequestTimeout(int requestTimeout) {
        this.requestTimeout = requestTimeout;
    }

    public String getCookies() {
        return cookies;
    }

    public void setCookies(String cookies) {
        this.cookies = cookies;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public void addHeader(String name, String value) {
        headers.put(name, value);
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }
}
