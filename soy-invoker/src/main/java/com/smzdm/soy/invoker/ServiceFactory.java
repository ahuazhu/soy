package com.smzdm.soy.invoker;

import com.smzdm.soy.domain.InvokerConfig;
import com.smzdm.soy.domain.ProxyServiceFactory;
import com.smzdm.soy.util.BuiltIn;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhengwenzhu on 2016/10/31.
 */
public class ServiceFactory {
    private static boolean initialized = false;

    static {
        if (!initialized) {
            synchronized (ServiceFactory.class) {
                if (!initialized) {
                    new com.smzdm.soy.domain.Module().init();
                    new com.smzdm.soy.http.Module().init();
                }
            }
        }
    }


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
