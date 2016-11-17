package com.smzdm.soy.http.domain.impl;

import com.smzdm.soy.domain.Invoker;
import com.smzdm.soy.domain.InvokerContext;
import com.smzdm.soy.exception.SoyException;
import com.smzdm.soy.exception.SoyResponseException;
import com.smzdm.soy.http.domain.HttpRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhengwenzhu on 2016/11/16.
 */
public class HttpInvoker implements Invoker {

    private HttpRequest httpRequest;

    public HttpInvoker(HttpRequest request) {
        this.httpRequest = request;
    }

    public String invoke(InvokerContext context) throws SoyException {
        return new HttpClient(httpRequest).invoke();
    }

    static class HttpClient {

        private final HttpUriRequest request;
        org.apache.http.client.HttpClient httpClient;

        public HttpClient(HttpRequest request) {
            this.request = make(request);
            httpClient = HttpClientBuilder.create().build();

        }

        public String invoke() {
            try {
                HttpResponse response = httpClient.execute(request);
                System.out.println(response.getEntity());
                if (response.getStatusLine() == null || response.getStatusLine().getStatusCode() != 200) {
                    throw new SoyResponseException();
                }

                return EntityUtils.toString(response.getEntity());

            } catch (IOException e) {
                throw new SoyException(e);
            }
        }


        private HttpUriRequest make(HttpRequest request) {

            List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
            if (request.getParameterMap() != null) {
                Map<String, List<String>> paramsMap = request.getParameterMap();

                for (String key : paramsMap.keySet()) {
                    for (String value : paramsMap.get(key)) {
                        params.add(new BasicNameValuePair(key, value));
                    }

                }
            }

            String param = URLEncodedUtils.format(params, "UTF-8");

            String url = concat(request.getUrl(), param);


            HttpUriRequest httpUriRequest = selectMethod(request, url);


            if (request.getHeaderMap() != null) {
                for (String head : request.getHeaderMap().keySet()) {
                    httpUriRequest.addHeader(head, request.getHeaderMap().get(head));
                }
            }

            return httpUriRequest;

        }

        private String concat(String url, String param) {

            if (url == null) {
                return null;
            }
            if (StringUtils.isBlank(param)) {
                return url;
            }
            if (url.endsWith("/")) {
                url = url.substring(0, url.length() - 1);
            }
            if (url.contains("?")) {
                url = url + "&" + param;
            } else {
                url = url + "?" + param;
            }
            return url;
        }

        private HttpUriRequest selectMethod(HttpRequest request, String url) {

            HttpUriRequest httpUriRequest = null;

            switch (request.getMethod()) {
                case GET:
                    httpUriRequest = new HttpGet(url);
                    break;
                case POST:
                    httpUriRequest = new HttpPost(url);
                    break;
                case PUT:
                    httpUriRequest = new HttpPut(url);
                    break;
                case HEAD:
                    httpUriRequest = new HttpHead(url);
                    break;
                case OPTION:
                    httpUriRequest = new HttpOptions(url);
                    break;
                case DELETE:
                    httpUriRequest = new HttpDelete(url);
                    break;
                default:
                    httpUriRequest = new HttpGet(url);
                    break;
            }

            return httpUriRequest;
        }


    }
}
