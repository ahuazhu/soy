package com.smzdm.soy.domain;

import com.smzdm.soy.util.BuiltIn;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhengwenzhu on 2016/11/16.
 */
public class ProxyServiceFactory {
    private static final Map<BuiltIn.Proto, ProxyService> proxyServices = new ConcurrentHashMap<BuiltIn.Proto, ProxyService>();

    public static ProxyService getProxyFactory(BuiltIn.Proto proto) {
        return proxyServices.get(proto);
    }

    public static void registerProxyService(BuiltIn.Proto proto, ProxyService proxyService) {
        proxyServices.put(proto, proxyService);
    }
}
