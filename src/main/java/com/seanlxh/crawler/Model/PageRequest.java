package com.seanlxh.crawler.Model;

import java.net.Proxy;
import java.util.Map;

public class PageRequest {
    private String url;
    private Map<String,String> paramMap;
    private Map<String,String> cookieMap;
    private Map<String,String> headerMap;
    private String userAgent;
    private String referrer;
    private boolean ifPost;
    private int timeoutMillis;
    private boolean isValidateTLSCertificates;
    private Proxy proxy;


}
