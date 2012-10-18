/**
 * @文件名称：PageFetcher.java
 * @类路径：com.eagleyes.crawler.fetcher
 * @版权:Copyright (c)2012
 * @作者：limeng
 * @时间：Oct 15, 20122:52:29 PM
 */
package com.eagleyes.crawler.fetcher;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cyberneko.html.parsers.DOMParser;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.dom4j.QName;
import org.dom4j.io.DOMReader;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.eagleyes.crawler.util.UserAgentUtil;





/**
 * @描述：
 * @作者：limeng
 * @创建时间：Oct 15, 20122:52:29 PM
 */
public class PageFetcher {

	private static Log          log                        = LogFactory.getLog(PageFetcher.class);

    // HTTP连接默认超时时间，单位为毫秒
    private static final int    DEFAULT_CONNECTION_TIMEOUT = 10000;
    // HTTP读操作默认超时时间，单位为毫秒
    private static final int    DEFAULT_SOCKET_TIMEOUT     = 10000;
    // 默认编码方式utf-8
    private static final String DEFAULT_ENCODING           = "utf-8";

    public static String fetchPageAsString(FetcherConfig config) {
        byte[] fetchResult = fetchPageAsByteArray(config);
        if (fetchResult == null) {
            return null;
        }
        try {
            return new String(fetchResult, DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException ignore) {
            log.error(ignore);
            return null;
        }
    }

    public static Document fetchPageAsDOM(FetcherConfig config) {
        byte[] fetchResult = fetchPageAsByteArray(config);
        if (fetchResult == null) {
            return null;
        }
        try {
            DOMParser parser = new DOMParser();
            // set default encoding
                                
            parser.setProperty("http://cyberneko.org/html/properties/default-encoding", DEFAULT_ENCODING);
            parser.parse(new InputSource(new ByteArrayInputStream(fetchResult)));
            org.w3c.dom.Document doc = parser.getDocument();
            DOMReader reader = new DOMReader();
            org.dom4j.Document document = reader.read(doc);
            removeNamespaces(document.getRootElement());
            return document;
        } catch (IOException e) {
            log.error("parse fetch result error", e);
        } catch (SAXException e) {
            log.error("parse fetch result error", e);
        }
        return null;
    }

	private static void removeNamespaces(Element elem) {
		setNamespaces(elem, Namespace.NO_NAMESPACE);
	}
	private static void setNamespaces(Element elem, Namespace ns) {
		setNamespace(elem, ns);
		setNamespaces(elem.content(), ns);
	}
	private static void setNamespace(Element elem, Namespace ns) {
		elem.setQName(QName.get(elem.getName(), ns, elem.getQualifiedName()));
	}
    
	@SuppressWarnings("rawtypes")
	private static void setNamespaces(List l, Namespace ns) {
		Node n = null;
		for (int i = 0; i < l.size(); i++) {
			n = (Node) l.get(i);
			if (n.getNodeType() == Node.ATTRIBUTE_NODE)
				((Attribute) n).setNamespace(ns);
			if (n.getNodeType() == Node.ELEMENT_NODE)
				setNamespaces((Element) n, ns);
		}
	}
	
	public static byte[] fetchPageAsByteArray(FetcherConfig config) {
        if (config == null) {
            return null;
        }
        String ip = config.getIp();
        String url = config.getUrl();
        if (ip == null || url == null) {
            return null;
        }
        HostConfiguration hostConf = new HostConfiguration();
        // set ip
        try {
            hostConf.setLocalAddress(InetAddress.getByName(ip));
        } catch (UnknownHostException e) {
            log.error("ip address is unknown", e);
            return null;
        }
        // set requestMethod and url
        HttpMethod httpMethod = null;
        if (FetcherConfig.REQUESTMETHOD_POST.equals(config.getRequestMethod())) {
            httpMethod = new PostMethod(url);
        } else {
            httpMethod = new GetMethod(url);
        }
        HttpClient httpclient = new HttpClient();
        // set connection timeout,default is 10s
        httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(DEFAULT_CONNECTION_TIMEOUT);
        // set socket timeout,default is 10s
        int requestTimeout = config.getRequestTimeout() * 1000;
        if (requestTimeout <= 0) {
            requestTimeout = DEFAULT_SOCKET_TIMEOUT;
        }
        httpMethod.getParams().setSoTimeout(requestTimeout);
        // set headers
        Map<String, String> headers = config.getHeaders();
        if (headers != null && !headers.isEmpty()) {
            for (Entry<String, String> entry : headers.entrySet()) {
                httpMethod.setRequestHeader(entry.getKey(), entry.getValue());
            }
        }
        // set cookies
        String cookies = config.getCookies();
        if (cookies != null) {
            httpMethod.setRequestHeader("Cookie", cookies);
        }
        // set User-Agent
        httpMethod.setRequestHeader("User-Agent", UserAgentUtil.getRandomUserAgent());
        // set Accept
        httpMethod.setRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        // set Accept-Language
        httpMethod.setRequestHeader("Accept-Language", "en-us;q=1");
        // set Accpet-Encoding
        httpMethod.setRequestHeader("Accpet-Encoding", "gzip,deflate");
        // set Accept-Charset
        httpMethod.setRequestHeader("Accept-Charset", "utf-8;q=1");
        // set Keep-Alive
        httpMethod.setRequestHeader("Keep-Alive", "115");
        httpMethod.setRequestHeader("Connection", "keep-alive");
        // set cookie policy
        httpMethod.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);

        // retry once while error occurrs
        httpMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new HttpMethodRetryHandler() {

            public boolean retryMethod(HttpMethod method, IOException exception, int executionCount) {
                if (executionCount < 2) {
                    log.error("error occurred,retry once.", exception);
                    return true;
                }
                return false;
            }
        });

        try {
            int statusCode = httpclient.executeMethod(hostConf, httpMethod);
            if (statusCode == HttpStatus.SC_OK) {
                return httpMethod.getResponseBody();
            } else {
                log.error("statusCode[" + statusCode + "] NOT OK");
            }
        } catch (Exception e) {
            log.error("fetch page error", e);
        } finally {
            httpMethod.releaseConnection();
        }
        return null;
    }
}
