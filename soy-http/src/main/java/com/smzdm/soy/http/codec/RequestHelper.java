package com.smzdm.soy.http.codec;

import com.smzdm.soy.domain.InvokerContext;
import com.smzdm.soy.http.domain.HttpRequest;

/**
 * Created by zhengwenzhu on 16/10/28.
 */
public interface RequestHelper {

    HttpRequest newRequest(InvokerContext context);

}
