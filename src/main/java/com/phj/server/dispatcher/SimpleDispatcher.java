package com.phj.server.dispatcher;

import com.phj.server.handle.Servlet;
import com.phj.server.model.net.NetConnect;
import com.phj.server.model.request.ServletRequest;
import com.phj.server.model.response.ServletResponse;
import com.phj.server.register.analysis.Analysis;

public class SimpleDispatcher {

    private static final Analysis mAnalysis = new Analysis();

    /**
     * 简单的分发器
     * @param servletRequest 请求参数的解析类
     * @param servletResponse 相应类
     */
    public static void dispatcher(ServletRequest servletRequest, ServletResponse servletResponse){

        System.out.println(servletRequest);
        Servlet servlet = mAnalysis.getServletByUrl(servletRequest.url().getUrl());
        if (servlet == null) {
            System.out.println("request is null,this url is "+servletRequest.url().getUrl());
            return;
        }
        servlet.service(servletRequest,servletResponse);



    }

}
