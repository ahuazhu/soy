package com.smzdm.soy.http.codec.impl;

import com.smzdm.soy.domain.InvocationRequest;
import com.smzdm.soy.domain.InvokerConfig;
import com.smzdm.soy.domain.InvokerContext;
import com.smzdm.soy.exception.SoyRequestException;
import com.smzdm.soy.http.annotation.Header;
import com.smzdm.soy.http.annotation.Http;
import com.smzdm.soy.http.annotation.Param;
import com.smzdm.soy.http.annotation.PathParam;
import com.smzdm.soy.http.codec.RequestHelper;
import com.smzdm.soy.http.domain.HttpRequest;
import com.smzdm.soy.http.domain.impl.DefaultHttpRequest;
import org.apache.commons.lang3.text.StrSubstitutor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhengwenzhu on 16/10/28.
 */
public class HttpRequestHelper implements RequestHelper {


    public static final String SOY_DEFAULT_PARAMETER_KEY = "_SOY_DEFAULT_PARAMETER_KEY_";

    public HttpRequest newRequest(InvokerContext context) {
        DefaultHttpRequest httpRequest = new DefaultHttpRequest();

        InvocationRequest request = context.getInvocationRequest();

        InvokerConfig config = context.getInvokerConfig();
        parse(config, httpRequest);

        Method method = request.getInvokeMethod();
        Http http = method.getAnnotation(Http.class);
        parse(http, httpRequest);

        parse(request, httpRequest);

        return httpRequest;
    }

    private void parse(InvocationRequest request, DefaultHttpRequest httpRequest) {
        Method method = request.getInvokeMethod();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        Class<?>[] argumentTypes = request.getInvokeArgumentTypes();
        Object[] arguments = request.getInvokeArguments();

        int argumentLength;
        if (arguments == null) {
            argumentLength = 0;
        } else {
            argumentLength = arguments.length;
        }

        for (int i = 0; i < argumentLength; i++) {
            Annotation[] annotations = parameterAnnotations[i];
            Object argument = arguments[i];
            Class<?> argumentType = argumentTypes[i];
            Map<String, String> map = parseMap(argument, argumentType);


            for (Annotation a : annotations) {
                if (a instanceof Param) {
                    addToParameter(httpRequest, map);
                    if (map.get(SOY_DEFAULT_PARAMETER_KEY) != null) {
                        httpRequest.addParameter(((Param) a).value(), map.get(SOY_DEFAULT_PARAMETER_KEY));
                    }
                }
                if (a instanceof Header) {
                    addToHeader(httpRequest, map);
                }
                if (a instanceof PathParam) {
                    resolvePathPlaceHolder(httpRequest, map);
                }
            }
        }
    }

    private void resolvePathPlaceHolder(DefaultHttpRequest httpRequest, Map<String, String> map) {
        String originUrl = httpRequest.getUrl();
        StrSubstitutor sub = new StrSubstitutor(map, "{", "}");
        String resolvedUrl = sub.replace(originUrl);
        httpRequest.setUrl(resolvedUrl);

    }

    private void addToParameter(DefaultHttpRequest httpRequest, Map<String, String> map) {
        for (String k : map.keySet()) {
            if (!SOY_DEFAULT_PARAMETER_KEY.equals(k)) {
                httpRequest.addParameter(k, map.get(k));
            }
        }

    }

    private void addToHeader(DefaultHttpRequest httpRequest, Map<String, String> map) {
        for (String k : map.keySet()) {
            httpRequest.addHeader(k, map.get(k));
        }
    }

    private Map<String, String> parseMap(Object argument, Class<?> argumentType) {
        Map<String, String> map = new HashMap<String, String>();
        if (argument instanceof Map) {
            Map m = (Map) argument;
            for (Object k : m.keySet()) {
                if (k.getClass() == String.class) {
                    Object v = m.get(k);
                    if (v != null) {
                        map.put((String) k, String.valueOf(v));
                    }
                } else {
                    throw new SoyRequestException("parameter key is not String " + k);
                }
            }
        } else if (isBasicType(argumentType)) {
            map.put(SOY_DEFAULT_PARAMETER_KEY, String.valueOf(argument));
        } else {
            Field[] fields = argumentType.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.isAccessible()) {
                    try {
                        if (field.get(argument) != null) {
                            map.put(field.getName(), String.valueOf(field.get(argument)));
                        }
                    } catch (IllegalAccessException e) {
                        throw new SoyRequestException(e);
                    }
                }
            }
        }
        return map;
    }

    private boolean isBasicType(Class<?> argumentType) {

        return argumentType == Integer.TYPE
                || argumentType == int.class
                || argumentType == Short.TYPE
                || argumentType == short.class
                || argumentType == Boolean.TYPE
                || argumentType == boolean.class
                || argumentType == Byte.TYPE
                || argumentType == byte.class
                || argumentType == Long.class
                || argumentType == long.class
                || argumentType == Float.class
                || argumentType == float.class
                || argumentType == Double.class
                || argumentType == double.class
                || argumentType == Character.class
                || argumentType == char.class
                || argumentType == String.class
                || argumentType.isAssignableFrom(CharSequence.class);


    }

    private void parse(InvokerConfig config, DefaultHttpRequest request) {
        String serviceName = config.getServiceName();
        request.setUrl(serviceName);
    }

    private void parse(Http http, DefaultHttpRequest request) {
        if (http != null) {
            request.setMethod(http.method());
            request.setUrl(request.getUrl() + http.path());
        } else {
            request.setMethod(com.smzdm.soy.http.domain.Method.GET);
        }
    }
}
