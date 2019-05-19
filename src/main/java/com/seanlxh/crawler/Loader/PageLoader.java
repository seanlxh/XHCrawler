package com.seanlxh.crawler.Loader;

import com.seanlxh.crawler.Model.PageRequest;
import org.jsoup.nodes.Document;

public abstract class PageLoader {
    public abstract Document load(PageRequest pageRequest);

}
