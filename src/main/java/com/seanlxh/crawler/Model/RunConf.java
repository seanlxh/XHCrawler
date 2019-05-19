package com.seanlxh.crawler.Model;

import com.seanlxh.crawler.Conf.CrawlerConf;
import com.seanlxh.crawler.Loader.PageLoader;
import com.seanlxh.crawler.Loader.strategy.JsoupPageLoader;
import com.seanlxh.crawler.Parser.PageParse;
import com.seanlxh.crawler.Proxy.ProxyMaker;
import com.seanlxh.crawler.Util.RegexUtil;
import com.seanlxh.crawler.Util.UrlUtil;

import java.util.*;

public class RunConf {
    private volatile boolean allowSpread = true;
    private Set<String> whiteUrlRegexs = Collections.synchronizedSet(new HashSet<String>());    // URL白名单正则，非空时进行URL白名单过滤页面
    private PageLoader pageLoader = new JsoupPageLoader();                          // 页面下载器
    private PageParse pageParser;

    private volatile Map<String, String> paramMap;                                  // 请求参数
    private volatile Map<String, String> cookieMap;                                 // 请求Cookie
    private volatile Map<String, String> headerMap;                                 // 请求Header
    private volatile List<String> userAgentList = Collections.synchronizedList(new ArrayList<String>(Arrays.asList(CrawlerConf.USER_AGENT_CHROME)));     // 请求UserAgent
    private volatile String referrer;                                               // 请求Referrer
    private volatile boolean ifPost = false;                                        // 请求方式：true=POST请求、false=GET请求
    private volatile int timeoutMillis = CrawlerConf.TIMEOUT_MILLIS_DEFAULT;     // 超时时间，毫秒
    private volatile int pauseMillis = 0;                                           // 停顿时间，爬虫线程处理完页面之后进行主动停顿，避免过于频繁被拦截；
    private volatile ProxyMaker proxyMaker;                                         // 代理生成器
    private volatile int failRetryCount = 0;                                        // 失败重试次数，大于零时生效
    private volatile boolean isValidateTLSCertificates = true;                      // 是否验证https



    public boolean validWhiteUrl(String link) {
        if (!UrlUtil.isUrl(link)) {
            return false;   // false if url invalid
        }

        if (whiteUrlRegexs != null && whiteUrlRegexs.size() > 0) {
            boolean underWhiteUrl = false;
            for (String whiteRegex : whiteUrlRegexs) {
                if (RegexUtil.matches(whiteRegex, link)) {
                    underWhiteUrl = true;
                }
            }
            if (!underWhiteUrl) {
                return false;   // check white
            }

        }
        return true;
    }


    public boolean isAllowSpread() {
        return allowSpread;
    }

    public void setAllowSpread(boolean allowSpread) {
        this.allowSpread = allowSpread;
    }

    public Set<String> getWhiteUrlRegexs() {
        return whiteUrlRegexs;
    }

    public void setWhiteUrlRegexs(Set<String> whiteUrlRegexs) {
        this.whiteUrlRegexs = whiteUrlRegexs;
    }

    public PageLoader getPageLoader() {
        return pageLoader;
    }

    public void setPageLoader(PageLoader pageLoader) {
        this.pageLoader = pageLoader;
    }

    public PageParse getPageParser() {
        return pageParser;
    }

    public void setPageParser(PageParse pageParser) {
        this.pageParser = pageParser;
    }

    public Map<String, String> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, String> paramMap) {
        this.paramMap = paramMap;
    }

    public Map<String, String> getCookieMap() {
        return cookieMap;
    }

    public void setCookieMap(Map<String, String> cookieMap) {
        this.cookieMap = cookieMap;
    }

    public Map<String, String> getHeaderMap() {
        return headerMap;
    }

    public void setHeaderMap(Map<String, String> headerMap) {
        this.headerMap = headerMap;
    }

    public List<String> getUserAgentList() {
        return userAgentList;
    }

    public void setUserAgentList(List<String> userAgentList) {
        this.userAgentList = userAgentList;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public boolean isIfPost() {
        return ifPost;
    }

    public void setIfPost(boolean ifPost) {
        this.ifPost = ifPost;
    }

    public int getTimeoutMillis() {
        return timeoutMillis;
    }

    public void setTimeoutMillis(int timeoutMillis) {
        this.timeoutMillis = timeoutMillis;
    }

    public int getPauseMillis() {
        return pauseMillis;
    }

    public void setPauseMillis(int pauseMillis) {
        this.pauseMillis = pauseMillis;
    }

    public ProxyMaker getProxyMaker() {
        return proxyMaker;
    }

    public void setProxyMaker(ProxyMaker proxyMaker) {
        this.proxyMaker = proxyMaker;
    }

    public int getFailRetryCount() {
        return failRetryCount;
    }

    public void setFailRetryCount(int failRetryCount) {
        this.failRetryCount = failRetryCount;
    }

    public boolean isValidateTLSCertificates() {
        return isValidateTLSCertificates;
    }

    public void setValidateTLSCertificates(boolean validateTLSCertificates) {
        isValidateTLSCertificates = validateTLSCertificates;
    }
}
