package com.smzdm.soy.http;

import com.smzdm.soy.AbstractModule;
import com.smzdm.soy.domain.ProxyServiceFactory;
import com.smzdm.soy.http.domain.HttpProxyService;
import com.smzdm.soy.util.BuiltIn;

/**
 * Created by zhengwenzhu on 2016/11/16.
 */
public class Module extends AbstractModule{

    public void init() {
        ProxyServiceFactory.registerProxyService(BuiltIn.Proto.HTTP, new HttpProxyService());
    }
}
