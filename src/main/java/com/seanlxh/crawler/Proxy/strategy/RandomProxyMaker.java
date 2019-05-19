package com.seanlxh.crawler.Proxy.strategy;

import com.seanlxh.crawler.Proxy.ProxyMaker;

import java.net.Proxy;
import java.util.Random;

public class RandomProxyMaker extends ProxyMaker {

    private Random random = new Random();

    @Override
    public Proxy make() {
        if (super.proxyList==null || super.proxyList.size()==0) {
            return null;
        }

        if (super.proxyList.size() == 1) {
            super.proxyList.get(0);
        }

        return super.proxyList.get(random.nextInt(super.proxyList.size()));
    }

}
