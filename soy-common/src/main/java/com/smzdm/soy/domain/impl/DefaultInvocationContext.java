package com.smzdm.soy.domain.impl;

import com.smzdm.soy.domain.InvocationRequest;
import com.smzdm.soy.domain.InvocationResponse;
import com.smzdm.soy.domain.InvokerConfig;
import com.smzdm.soy.domain.InvokerContext;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhengwenzhu on 2016/11/16.
 */
public class DefaultInvocationContext implements InvokerContext {

    private InvokerConfig invokerConfig;

    private InvocationRequest invocationRequest;

    private InvocationResponse invocationResponse;

    private Map<String, Serializable> context = new HashMap<String, Serializable>();

    public DefaultInvocationContext(InvokerConfig config) {
        this.invokerConfig = config;
    }

    public InvokerConfig getInvokerConfig() {
        return invokerConfig;
    }

    public void setInvokerConfig(InvokerConfig invokerConfig) {
        this.invokerConfig = invokerConfig;
    }

    public InvocationRequest getInvocationRequest() {
        return invocationRequest;
    }

    public void setInvocationRequest(InvocationRequest invocationRequest) {
        this.invocationRequest = invocationRequest;
    }

    public InvocationResponse getInvocationResponse() {
        return invocationResponse;
    }

    public void setInvocationResponse(InvocationResponse invocationResponse) {
        this.invocationResponse = invocationResponse;
    }

    public Serializable getContextValue(String key) {
        return context.get(key);
    }

    public void putContextValue(String key, Serializable value) {
        context.put(key, value);
    }

    public Map<String, Serializable> getContextValues() {
        return context;
    }
}
