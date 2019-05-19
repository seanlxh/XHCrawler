package com.seanlxh.crawler.Parser.strategy;

import com.seanlxh.crawler.Parser.PageParse;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public abstract class NonPageParser extends PageParse {
    @Override
    public void parse(Document html, Element pageVoElement, Object pageVo) {
        // TODO，not parse page, output page source
    }

    public abstract void parse(String url, String pageSource);

}
