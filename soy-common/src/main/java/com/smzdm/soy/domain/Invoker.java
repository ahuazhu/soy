package com.smzdm.soy.domain;

import com.smzdm.soy.exception.SoyException;

/**
 * Created by zhengwenzhu on 16/10/28.
 */
public interface Invoker {

    String invoke(InvokerContext context) throws SoyException;

}
