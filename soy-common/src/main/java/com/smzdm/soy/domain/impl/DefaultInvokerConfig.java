package com.smzdm.soy.domain.impl;

import com.smzdm.soy.domain.InvokerConfig;
import com.smzdm.soy.util.BuiltIn;

/**
 * Created by zhengwenzhu on 2016/11/9.
 */
public class DefaultInvokerConfig<T> implements InvokerConfig<T> {

    private Class<T> invokeInterface;

    private String serviceName;

    private int timeout;

    private BuiltIn.CallType callType;

    private BuiltIn.Serialize serialize;

    private BuiltIn.Proto proto;


    public Class<T> getInterface() {
        return invokeInterface;
    }

    public void setInterface(Class<T> invokeInterface) {
        this.invokeInterface = invokeInterface;
    }


    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public BuiltIn.CallType getCallType() {
        return callType;
    }

    public void setCallType(BuiltIn.CallType callType) {
        this.callType = callType;
    }

    public BuiltIn.Serialize getSerialize() {
        return serialize;
    }

    public void setSerialize(BuiltIn.Serialize serialize) {
        this.serialize = serialize;
    }

    public Class<T> getInvokeInterface() {
        return invokeInterface;
    }

    public void setInvokeInterface(Class<T> invokeInterface) {
        this.invokeInterface = invokeInterface;
    }

    public BuiltIn.Proto getProto() {
        return proto;
    }

    public void setProto(BuiltIn.Proto proto) {
        this.proto = proto;
    }
}
