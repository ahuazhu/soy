package com.smzdm.soy.invoker;

import com.smzdm.soy.domain.InvokerContext;
import com.smzdm.soy.exception.SoyException;

/**
 * Created by zhengwenzhu on 16/10/28.
 */
public interface Invoker {

    String invoke(InvokerContext context) throws SoyException;

}
