package com.phj.server.handle;


import com.phj.server.model.request.ServletRequest;
import com.phj.server.model.response.ServletResponse;
import com.phj.server.model.servlet.ServletConfig;

/**
 * 描述: 仿造Servlet
 * create: PHJ
 * time:2019/5/29 20:02
 */
public interface Servlet {

    // 初始化
    void init(ServletConfig var1) ;

    ServletConfig getServletConfig();

    // 执行
    void service(ServletRequest var1, ServletResponse var2) ;

    // 销毁
    void destroy();
}

