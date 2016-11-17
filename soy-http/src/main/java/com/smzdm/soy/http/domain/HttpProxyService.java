package com.smzdm.soy.http.domain;

import com.google.gson.Gson;
import com.smzdm.soy.domain.*;
import com.smzdm.soy.domain.impl.DefaultInvocationContext;
import com.smzdm.soy.http.codec.impl.DefaultChannelRequestCodec;
import com.smzdm.soy.http.domain.impl.HttpInvoker;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by zhengwenzhu on 2016/11/16.
 */
public class HttpProxyService<S> implements ProxyService<S> {

    public Object proxyService(InvokerConfig<S> invokerConfig) throws ClassNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class<?> type = invokerConfig.getInterface();
        return Proxy.newProxyInstance(classLoader, new Class[]{type}, new HttpInvocationHandler(invokerConfig));
    }


    static class HttpInvocationHandler implements InvocationHandler {


        private InvokerConfig invokerConfig;

        public HttpInvocationHandler(InvokerConfig invokerConfig) {
            this.invokerConfig = invokerConfig;
        }

        public Object invoke(Object proxy, final java.lang.reflect.Method method, final Object[] args) throws Throwable {

            InvokerContext context = new DefaultInvocationContext(invokerConfig);
            context.setInvocationRequest(new InvocationRequest() {
                public Object getInvokeInterface() {
                    return invokerConfig.getInterface();
                }

                public Method getInvokeMethod() {
                    return method;
                }

                public Class<?>[] getInvokeArgumentTypes() {
                    return method.getParameterTypes();
                }

                public Object[] getInvokeArguments() {
                    return args;
                }
            });

            context.setInvocationResponse(new InvocationResponse() {
            });

            HttpRequest request = new DefaultChannelRequestCodec().newRequest(context);

            String result = new HttpInvoker(request).invoke(context);

            return new Gson().fromJson(result, method.getReturnType());
        }
    }

}
