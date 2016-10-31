package com.smzdm.soy.domain.impl;

import com.smzdm.soy.domain.Serializer;
import com.smzdm.soy.domain.Invoker;
import com.smzdm.soy.domain.InvokerContext;
import com.smzdm.soy.exception.SoyException;

/**
 * Created by zhengwenzhu on 2016/10/31.
 */
public abstract class AbstractInvoker implements Invoker {

    protected Serializer codec;

    public AbstractInvoker(Serializer serializer) {
        this.codec = serializer;
    }

    public void invoke(InvokerContext context) throws SoyException {

    }
}
