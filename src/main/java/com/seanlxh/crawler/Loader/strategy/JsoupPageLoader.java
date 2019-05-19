package com.seanlxh.crawler.Loader.strategy;

import com.seanlxh.crawler.Loader.PageLoader;
import com.seanlxh.crawler.Model.PageRequest;
import com.seanlxh.crawler.Util.JsoupUtil;
import org.jsoup.nodes.Document;

/**
 * jsoup page loader
 *
 * @author xuxueli 2017-12-28 00:29:49
 */
public class JsoupPageLoader extends PageLoader {

    @Override
    public Document load(PageRequest pageRequest) {
        return JsoupUtil.load(pageRequest);
    }

}
