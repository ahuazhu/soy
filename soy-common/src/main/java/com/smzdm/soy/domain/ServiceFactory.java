package com.smzdm.soy.domain;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhengwenzhu on 2016/11/15.
 */
public class ServiceFactory {

    private static Map<InvokerConfig<?>, Object> services = new ConcurrentHashMap<InvokerConfig<?>, Object>();

    @SuppressWarnings("unchecked")
    public static <T> T getService(InvokerConfig<T> config) {
        checkConfig(config);

        Object service = services.get(config);
        if (service == null) {
            try {
                service = ProxyServiceFactory.getProxyFactory(config.getProto()).proxyService(config);
            } catch (Exception e) {
                return null;
            }
            services.put(config, service);
        }
        return (T) service;
    }

    private static <T> void checkConfig(InvokerConfig<T> config) {

    }
}
