package com.phj.server.register;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述: URL和处理的Servlet的绑定关系的数据中心
 * create: PHJ
 * time:2019/5/29 19:50
 */
public class ServletContext {
    //为每一个servlet取个别名
    // login  -->com.bjsxt.server.demo03.LoginServlet
    private final Map<String, String> servlet;
    //url -->login
    //   /log -->login
    //   /login -->login
    private final Map<String, String> mapping;

    ServletContext() {
        servlet = new HashMap<String, String>();
        mapping = new HashMap<String, String>();
    }


    public Map<String, String> getServlet() {
        return servlet;
    }


    public Map<String, String> getMapping() {
        return mapping;
    }


}