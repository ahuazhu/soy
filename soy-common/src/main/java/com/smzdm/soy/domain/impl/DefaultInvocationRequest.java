package com.smzdm.soy.domain.impl;

import com.smzdm.soy.domain.InvocationRequest;

import java.lang.reflect.Method;

/**
 * Created by zhengwenzhu on 2016/10/31.
 */
public class DefaultInvocationRequest<T> implements InvocationRequest<T> {

    private T invokeInterface;

    private Method method;

    private Class<?>[] argumentTypes;

    private Object[] arguments;

    public DefaultInvocationRequest(T invokeInterface, Method method, Class<?>[] argumentTypes, Object[] arguments) {
        this.invokeInterface = invokeInterface;
        this.method = method;
        this.argumentTypes = argumentTypes;
        this.arguments = arguments;
    }

    public T getInvokeInterface() {
        return invokeInterface;
    }

    public Method getInvokeMethod() {
        return method;
    }

    public Class<?>[] getInvokeArgumentTypes() {
        return argumentTypes;
    }

    public Object[] getInvokeArguments() {
        return arguments;
    }

}
