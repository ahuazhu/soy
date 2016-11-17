package com.smzdm.soy.domain;

import com.smzdm.soy.util.BuiltIn;
import com.smzdm.soy.util.BuiltIn.CallType;
import com.smzdm.soy.util.BuiltIn.Serialize;

/**
 * Created by zhengwenzhu on 16/10/27.
 */
public interface InvokerConfig<S> {

    Class<S> getInterface();

    void setInterface(Class<S> invokeInterface);

    String getServiceName();

    void setServiceName(String serviceId);

    int getTimeout();

    void setTimeout(int timeout);

    CallType getCallType();

    void setCallType(CallType callType);

    Serialize getSerialize();

    void setSerialize(Serialize serialize);

    BuiltIn.Proto getProto();

    void setProto(BuiltIn.Proto proto);
}
