package com.smzdm.soy.http.codec.impl;

import com.smzdm.soy.domain.InvokerContext;
import com.smzdm.soy.http.codec.HttpChannelRequestCodec;
import com.smzdm.soy.http.domain.HttpRequest;
import com.smzdm.soy.http.domain.impl.DefaultHttpRequest;

/**
 * Created by zhengwenzhu on 16/10/28.
 */
public class DefaultChannelRequestCodec implements HttpChannelRequestCodec {

    public HttpRequest newRequest(InvokerContext context) {
        DefaultHttpRequest request = new DefaultHttpRequest();



        return request;
    }
}
