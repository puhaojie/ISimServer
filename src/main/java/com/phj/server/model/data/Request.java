package com.phj.server.model.data;


/**
 * 描述: http请求消息的解析结果类
 * create: PHJ
 * time:2019/5/28 19:53
 */
public class Request {

    final HttpUrl url;
    final String method;
    final Headers headers;
    final RequestBody body;

    Request(Builder builder) {
        this.url = builder.url;
        this.method = builder.method;
        this.headers = builder.headers;
        this.body = builder.body;
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


    public static class Builder {
        HttpUrl url;
        String method;
        Headers headers;
        RequestBody body;
        Object tag;

        public Builder() {
            this.method = "GET";
            this.headers = new Headers();
        }


        public Builder url(HttpUrl url) {
            if (url == null) throw new NullPointerException("url == null");
            this.url = url;
            return this;
        }



        public Request build() {
            if (url == null) throw new IllegalStateException("url == null");
            return new Request(this);
        }
    }
}
