package com.smzdm.soy.http.domain;

import java.util.Map;

/**
 * Created by zhengwenzhu on 16/10/28.
 */
public interface HttpResponse {

    String getBody();

    Map<String, String> getHeaderMap();

    int getHttpCode();
}
