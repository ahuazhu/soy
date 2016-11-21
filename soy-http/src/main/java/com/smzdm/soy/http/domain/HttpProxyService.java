package com.smzdm.soy.http.domain;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import com.smzdm.soy.domain.*;
import com.smzdm.soy.domain.impl.DefaultInvocationContext;
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

        private HttpInvoker invoker = new HttpInvoker();

        public HttpInvocationHandler(InvokerConfig invokerConfig) {
            this.invokerConfig = invokerConfig;
        }

        public Object invoke(Object proxy, final java.lang.reflect.Method method, final Object[] args) throws Throwable {

            Transaction t = Cat.newTransaction("Call", method.getName());

            try {
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

                String result = invoker.invoke(context);

                Object r = CodecFactory.getCodec(invokerConfig.getSerialize().name()).decode(result, method.getReturnType());

                t.setStatus(Transaction.SUCCESS);
                return r;
            } catch (Throwable e) {
                t.setStatus(e);
                throw e;
            } finally {
                t.complete();
            }
        }
    }

}
