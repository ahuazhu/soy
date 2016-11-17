package com.smzdm.soy.domain;

/**
 * Created by zhengwenzhu on 2016/11/16.
 */
public interface ProxyService<S> {

    Object proxyService(InvokerConfig<S> invokerConfig) throws ClassNotFoundException;

}
