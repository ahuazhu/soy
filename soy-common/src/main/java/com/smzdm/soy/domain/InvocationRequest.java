package com.smzdm.soy.domain;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by zhengwenzhu on 16/10/27.
 */
public interface InvocationRequest<T> {

    T getInvokeInterface();

    Method getInvokeMethod();

    Class<?>[] getInvokeArgumentTypes();

    Object[] getInvokeArguments();

    Annotation[] getInvokeClassAnnotations();

    Annotation[][] getInvokeArgumentsAnnotations();
}
