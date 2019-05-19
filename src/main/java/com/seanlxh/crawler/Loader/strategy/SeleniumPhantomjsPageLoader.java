package com.seanlxh.crawler.Loader.strategy;

import com.seanlxh.crawler.Loader.PageLoader;
import com.seanlxh.crawler.Model.PageRequest;
import com.seanlxh.crawler.Util.UrlUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SeleniumPhantomjsPageLoader extends PageLoader {
    private static Logger logger = LoggerFactory.getLogger(SeleniumPhantomjsPageLoader.class);

    private String driverPath;
    public SeleniumPhantomjsPageLoader(String driverPath) {
        this.driverPath = driverPath;
    }

    @Override
    public Document load(PageRequest pageRequest) {
        if (!UrlUtil.isUrl(pageRequest.getUrl())) {
            return null;
        }

        DesiredCapabilities dcaps = new DesiredCapabilities();
        dcaps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, !pageRequest.isValidateTLSCertificates());
        dcaps.setCapability(CapabilityType.TAKES_SCREENSHOT, false);
        dcaps.setCapability(CapabilityType.SUPPORTS_FINDING_BY_CSS, true);
        dcaps.setJavascriptEnabled(true);
        if (driverPath != null && driverPath.trim().length() > 0) {
            dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, driverPath);
        }

        if (pageRequest.getProxy() != null) {
            dcaps.setCapability(CapabilityType.ForSeleniumServer.AVOIDING_PROXY, true);
            dcaps.setCapability(CapabilityType.ForSeleniumServer.ONLY_PROXYING_SELENIUM_TRAFFIC, true);
            System.setProperty("http.nonProxyHosts", "localhost");
            dcaps.setCapability(CapabilityType.PROXY, pageRequest.getProxy());
        }


        WebDriver webDriver = new PhantomJSDriver(dcaps);
        try {
            webDriver.get(pageRequest.getUrl());
            if (pageRequest.getCookieMap() != null && !pageRequest.getCookieMap().isEmpty()) {
                for (Map.Entry<String, String> item : pageRequest.getCookieMap().entrySet()) {
                    webDriver.manage().addCookie(new Cookie(item.getKey(), item.getValue()));
                }
            }
            webDriver.manage().timeouts().implicitlyWait(pageRequest.getTimeoutMillis(), TimeUnit.MILLISECONDS);
            webDriver.manage().timeouts().pageLoadTimeout(pageRequest.getTimeoutMillis(), TimeUnit.MILLISECONDS);
            webDriver.manage().timeouts().setScriptTimeout(pageRequest.getTimeoutMillis(), TimeUnit.MILLISECONDS);

            String pageSource = webDriver.getPageSource();
            if (pageSource != null) {
                Document html = Jsoup.parse(pageSource);
                return html;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (webDriver != null) {
                webDriver.quit();
            }
        }
        return null;


    }

}
