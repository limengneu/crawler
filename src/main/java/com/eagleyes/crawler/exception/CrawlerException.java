/**
 * @文件名称：CrawlerException.java
 * @类路径：com.eagleyes.crawler.exception
 * @版权:Copyright (c)2012
 * @作者：limeng
 * @时间：Oct 15, 20122:17:06 PM
 */
package com.eagleyes.crawler.exception;

/**
 * @描述：
 * @作者：limeng
 * @创建时间：Oct 15, 20122:17:06 PM
 */
public class CrawlerException extends RuntimeException{

	/**
	 * @FieldsserialVersionUID:TODO
	 */
	private static final long serialVersionUID = 5608384024065850071L;
	
    public CrawlerException() {
    }

    public CrawlerException(String message) {
        super(message);
    }

    public CrawlerException(Throwable cause) {
        this(cause.getMessage(), cause);
    }

    public CrawlerException(String message, Throwable cause) {
        super(message, cause);
    }

}
