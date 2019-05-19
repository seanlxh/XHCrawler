package com.seanlxh.crawler.Annotation;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface Page {
    public String cssQuery() default "";
}
