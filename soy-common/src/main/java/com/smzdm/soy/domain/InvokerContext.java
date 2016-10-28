package com.smzdm.soy.domain;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by zhengwenzhu on 16/10/27.
 */

public interface InvokerContext<S> {

    InvokerConfig<S> getInvokerConfig();

    InvocationRequest getInvocationRequest();

    InvocationResponse getInvocationResponse();

    void setInvocationRequest(InvocationRequest request);

    void setInvocationResponse(InvocationResponse response);

    Serializable getContextValue(String key);

    void putContextValue(String key, Serializable value);

    Map<String, Serializable> getContextValues();

}
