package com.seanlxh.crawler;

import com.seanlxh.crawler.Loader.PageLoader;
import com.seanlxh.crawler.Model.RunConf;
import com.seanlxh.crawler.Parser.PageParse;
import com.seanlxh.crawler.Rundata.RunData;
import com.seanlxh.crawler.Rundata.strategy.LocalRunData;
import com.seanlxh.crawler.Thread.CrawlerThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainCrawler {
    private static Logger logger = LoggerFactory.getLogger(MainCrawler.class);
    // run data
    private volatile RunData runData = new LocalRunData();                          // 运行时数据模型

    // run conf
    private volatile RunConf runConf = new RunConf();                               // 运行时配置

    // thread
    private int threadCount = 1;                                                    // 爬虫线程数量
    private ExecutorService crawlers = Executors.newCachedThreadPool();             // 爬虫线程池
    private List<CrawlerThread> crawlerThreads = new CopyOnWriteArrayList<CrawlerThread>();     // 爬虫线程引用镜像


    public RunData getRunData() {
        return runData;
    }

    public RunConf getRunConf() {
        return runConf;
    }

    public static class Builder {
        private MainCrawler crawler = new MainCrawler();


        public Builder setRunData(RunData runData){
            crawler.runData = runData;
            return this;
        }


        public Builder setUrls(String... urls) {
            if (urls!=null && urls.length>0) {
                for (String url: urls) {
                    crawler.runData.addUrl(url);
                }
            }
            return this;
        }

        public Builder setAllowSpread(boolean allowSpread) {
            crawler.runConf.setAllowSpread(allowSpread);
            return this;
        }

        public Builder setWhiteUrlRegexs(String... whiteUrlRegexs) {
            if (whiteUrlRegexs!=null && whiteUrlRegexs.length>0) {
                for (String whiteUrlRegex: whiteUrlRegexs) {
                    crawler.runConf.getWhiteUrlRegexs().add(whiteUrlRegex);
                }
            }
            return this;
        }


        public Builder setPageParser(PageParse pageParser){
            crawler.runConf.setPageParser(pageParser);
            return this;
        }

        public Builder setPageLoader(PageLoader pageLoader){
            crawler.runConf.setPageLoader(pageLoader);
            return this;
        }

        public Builder setParamMap(Map<String, String> paramMap){
            crawler.runConf.setParamMap(paramMap);
            return this;
        }


    }

















}
