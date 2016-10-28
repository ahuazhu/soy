package com.smzdm.soy.http.domain;

import java.util.List;
import java.util.Map;

/**
 * Created by zhengwenzhu on 16/10/28.
 */
public interface HttpRequest {

    Method getMethod();

    String getUrl();

    Map<String, List<String>> getParameterMap();

    Map<String, String> getHeaderMap();

    Object getData();
}
