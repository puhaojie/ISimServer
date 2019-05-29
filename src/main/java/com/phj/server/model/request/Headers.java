package com.phj.server.model.request;


import java.util.HashMap;

/**
 * 描述: http请求头
 * create: PHJ
 * time:2019/5/28 19:53
 */
public final class Headers {

    final HashMap<String,String> headerMap = new HashMap<>(20);

    public void add(String key,String value) {
        headerMap.put(key,value);
    }

}
