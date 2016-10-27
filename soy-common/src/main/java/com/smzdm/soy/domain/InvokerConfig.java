package com.smzdm.soy.domain;

import com.smzdm.soy.util.BuiltIn.CallType;
import com.smzdm.soy.util.BuiltIn.Serialize;

/**
 * Created by zhengwenzhu on 16/10/27.
 */
public interface InvokerConfig<S> {

    String getServiceName();

    void setServiceName(String serviceId);

    int getTimeout();

    void setTimeout(int timeout);

    CallType getCallType();

    void setCallType(CallType callType);

    Serialize getSerialize();

    void setSerialize(Serialize serialize);
}
