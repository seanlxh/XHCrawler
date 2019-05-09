package com.seanlxh.crawler.Loader;

import org.jsoup.nodes.Document;

public abstract class PageLoader {
    public abstract Document load(PageRequest pageRequest);

}
