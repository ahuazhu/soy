package com.smzdm.soy.http.domain.impl;

import com.smzdm.soy.http.domain.HttpRequest;
import com.smzdm.soy.http.domain.Method;

import java.util.*;

/**
 * Created by zhengwenzhu on 16/10/28.
 */
@SuppressWarnings("unchecked")
public class DefaultHttpRequest implements HttpRequest {

    private String url;

    private Map<String, List<String>> parameterMap = new HashMap<String, List<String>>();

    private Map<String, String> headerMap = new HashMap<String, String>();

    private Object data;

    private Method method;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, List<String>> getParameterMap() {
        return parameterMap;
    }

    public void addParameter(String key, List<String> values) {
        if (key != null && values != null) {
            parameterMap.put(key, values);
        }
    }

    public void addParameter(String key, String value) {
        if (key != null && value != null) {
            List<String> values = parameterMap.get(key);
            if (values == null) {
                values = new LinkedList<String>();
                parameterMap.put(key, values);
            }
            values.add(value);
        }
    }

    public void setParameter(String key, String value) {
        if (key != null && value != null) {
            parameterMap.put(key, Collections.singletonList(value));
        }
    }


    public Map<String, String> getHeaderMap() {
        return headerMap;
    }

    public void setHeaderMap(Map<String, String> headerMap) {
        this.headerMap = headerMap;
    }

    public void addHeader(String key, String value) {
        headerMap.put(key, value);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
