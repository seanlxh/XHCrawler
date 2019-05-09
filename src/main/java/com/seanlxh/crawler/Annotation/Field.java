package com.seanlxh.crawler.Annotation;

import com.seanlxh.crawler.Conf.CrawlerConf;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface Field {
    public String cssQuery() default "";
    public CrawlerConf.SelectType selectType() default CrawlerConf.SelectType.TEXT;
    public String selectVal() default "";
    String datePattern() default "yyyy-MM-dd HH:mm:ss";
}
