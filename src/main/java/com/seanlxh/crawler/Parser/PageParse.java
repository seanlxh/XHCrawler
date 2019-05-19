package com.seanlxh.crawler.Parser;

import com.seanlxh.crawler.Model.PageRequest;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public abstract class PageParse<T> {
    public void preParse(PageRequest pageRequest) {
        // TODO
    }

    public abstract void parse(Document html, Element pageVoElement, T pageVo);


}
