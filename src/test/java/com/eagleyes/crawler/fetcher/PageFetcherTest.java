/**
 * @文件名称：PageFetcherTest.java
 * @类路径：com.eagleyes.crawler.fetcher
 * @版权:Copyright (c)2012
 * @作者：limeng
 * @时间：Oct 15, 20122:58:31 PM
 */
package com.eagleyes.crawler.fetcher;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Node;
import org.junit.Test;

import com.eagleyes.crawler.util.CategoryUtil;

import sun.net.util.IPAddressUtil;


/**
 * @描述：
 * @作者：limeng
 * @创建时间：Oct 15, 20122:58:31 PM
 */
public class PageFetcherTest {

    private String              localAddress;
    private static final String DEFAULT_URL = "http://hbszdfzyc.com";


    @Test
    public void testFetch() {
    	   // InetAddress.getLocalHost().getHostAddress() can not work in linux
        try {
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> ips = ni.getInetAddresses();
                while (ips.hasMoreElements()) {
                    String address = ips.nextElement().getHostAddress();
                    if (!"127.0.0.1".equals(address) && IPAddressUtil.isIPv4LiteralAddress(address)) {
                        localAddress = address;
                        break;
                    }
                }
            }
        } catch (SocketException ignore) {
        }
        System.out.println("*****************");
        System.out.println(localAddress);
        FetcherConfig config = new FetcherConfig();
        config.setIp(localAddress);
        config.setUrl(DEFAULT_URL);
        config.setRequestTimeout(10);
        String result = PageFetcher.fetchPageAsString(config);
        System.out.println(result);
        assertNotNull(result);
    }

    @Test
    public void testFetchCategoryDOM() throws Exception {
    	  try {
              Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
              while (netInterfaces.hasMoreElements()) {
                  NetworkInterface ni = netInterfaces.nextElement();
                  Enumeration<InetAddress> ips = ni.getInetAddresses();
                  while (ips.hasMoreElements()) {
                      String address = ips.nextElement().getHostAddress();
                      if (!"127.0.0.1".equals(address) && IPAddressUtil.isIPv4LiteralAddress(address)) {
                          localAddress = address;
                          break;
                      }
                  }
              }
          } catch (SocketException ignore) {
          }
        FetcherConfig config = new FetcherConfig();
        config.setIp(localAddress);
        config.setUrl(DEFAULT_URL);
        config.setRequestTimeout(10);
        Document result = PageFetcher.fetchPageAsDOM(config);
       
        System.out.println(result.getRootElement());
        List<Node> nodes = result.selectNodes("//DIV[@id='ProductMenuDiv']//@href");
        for(Node node:nodes){
        	String jsStatement = node.getStringValue().trim();
  		  System.out.println(jsStatement);
        }
        
    }
    
    @Test
    public void testFetchCategoryListDOM() throws Exception {
    	  try {
              Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
              while (netInterfaces.hasMoreElements()) {
                  NetworkInterface ni = netInterfaces.nextElement();
                  Enumeration<InetAddress> ips = ni.getInetAddresses();
                  while (ips.hasMoreElements()) {
                      String address = ips.nextElement().getHostAddress();
                      if (!"127.0.0.1".equals(address) && IPAddressUtil.isIPv4LiteralAddress(address)) {
                          localAddress = address;
                          break;
                      }
                  }
              }
          } catch (SocketException ignore) {
          }
        FetcherConfig config = new FetcherConfig();
        config.setIp(localAddress);
        config.setUrl("http://hbszdfzyc.com/Products-All");
        config.setRequestTimeout(10);
        Document result = PageFetcher.fetchPageAsDOM(config);
       

       
        String jsStatement =result.selectSingleNode("//DIV[@id='main']//SCRIPT").getStringValue().substring("/*摆臂式垃圾车*/ var product_list=new Array();".length());
       
        int count=1;
        String[] splits=StringUtils.split(jsStatement, ");");
        	for(int i=0;i<splits.length;i++){
        		String item=splits[i];
        		item=StringUtils.replace(item, "product_list.push(", "");
        		item=StringUtils.trim(item);
        		if(StringUtils.contains(item, "{")){
        		JSONObject jSONObject = JSONObject.fromObject(item);
//        		System.out.println("ID: "+jSONObject.get("ID"));
//        		System.out.println("TITLE: "+jSONObject.get("TITLE"));
//        		System.out.println("IMAGE: "+jSONObject.get("IMAGE"));
//        		System.out.println("MODEL: "+jSONObject.get("MODEL"));
        		if(StringUtils.isBlank(CategoryUtil.getCategoryValue(jSONObject.get("ClassName").toString()))){
        		System.out.println("ClassName: "+jSONObject.get("ClassName"));
        		}
        		count++;
        		}
        }
        	
        	System.out.println("the tatol count is :"+count);
    }
    
    @Test
    public void testFetchDetailDOM() throws Exception {
    	  try {
              Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
              while (netInterfaces.hasMoreElements()) {
                  NetworkInterface ni = netInterfaces.nextElement();
                  Enumeration<InetAddress> ips = ni.getInetAddresses();
                  while (ips.hasMoreElements()) {
                      String address = ips.nextElement().getHostAddress();
                      if (!"127.0.0.1".equals(address) && IPAddressUtil.isIPv4LiteralAddress(address)) {
                          localAddress = address;
                          break;
                      }
                  }
              }
          } catch (SocketException ignore) {
          }
        FetcherConfig config = new FetcherConfig();
        config.setIp(localAddress);
        config.setUrl("http://hbszdfzyc.com/Product-191415");
        config.setRequestTimeout(10);
        Document result = PageFetcher.fetchPageAsDOM(config);
       
//     
//        List<Node> nodes = result.selectNodes("//DIV[@id='pdetail']//TBODY//TR[@align='left']//TD");
//        for(Node node:nodes){
//        	String jsStatement = node.getStringValue().trim();
////  		  System.out.println(jsStatement);
////  		System.out.println("---------------");
//        }
//        
//        List<Node> lnodes = result.selectNodes("//DIV[@id='pdetail']//TBODY//TR//TD[@valign='center']");
////        for(Node node:lnodes){
////        	String jsStatement = node.getStringValue().trim();
//////  		  System.out.println(jsStatement);
//////  		System.out.println("*****************");
////        }
//        
//        List<Node> fdnodes = result.selectNodes("//DIV[@id='pdetail']/CENTER/TABLE/TBODY/TR/TD/TABLE//TD");
//        for(Node node:fdnodes){
////        	String jsStatement = node.getStringValue().trim();
////        	jsStatement=StringUtils.replace(jsStatement, "\n\t", "<br>");
////        	jsStatement=StringUtils.replace(jsStatement, "\t", "");
////  		  System.out.println(jsStatement);
//        }
        
        
        String image1 =result.selectSingleNode("//IMG[@id='p1']//@src").getStringValue();
        System.out.println(image1);
        
        String imageUrl=DEFAULT_URL+image1;
        config.setUrl(imageUrl);
        
        byte[]  iamge= PageFetcher.fetchPageAsByteArray(config);
        File storeFile = new File("/home/limeng/work/tzc/src/main/webapp/statics/image"+image1);  
     FileUtils.touch(storeFile);
     FileUtils.writeByteArrayToFile(storeFile, iamge);


        
        List<Node> imgnodes = result.selectNodes("//IMG[@title='后部照片']//@src");
        for(Node node:imgnodes){
        	String jsStatement = node.getStringValue().trim();
        
  		  System.out.println(jsStatement);
        }
        
    }
}
