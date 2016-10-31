package com.smzdm.soy.domain;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * Created by zhengwenzhu on 16/10/27.
 */
public interface InvocationRequest<T> {

    T getInvokeInterface();

    Method getInvokeMethod();

    Class<?>[] getInvokeArgumentTypes();

    Object[] getInvokeArguments();
}
