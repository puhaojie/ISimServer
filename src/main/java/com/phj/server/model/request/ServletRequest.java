package com.phj.server.model.request;


/**
 * 描述: http请求消息的解析结果类
 * create: PHJ
 * time:2019/5/28 19:53
 */
public class ServletRequest {

    private final HttpUrl url;
    private final String method;
    private final Headers headers;
    private final RequestBody body;

    public ServletRequest(HttpUrl url, String method, Headers headers, RequestBody body) {
        this.url = url;
        this.method = method;
        this.headers = headers;
        this.body = body;
    }

    public HttpUrl url() {
        return url;
    }

    public String method() {
        return method;
    }

    public Headers headers() {
        return headers;
    }

    public RequestBody body(){
        return body;
    }

    public long getDateHeader(String s) {
        return 0;
    }
}
