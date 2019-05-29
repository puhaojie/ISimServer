package com.phj.server.handle;

import com.phj.server.model.request.ServletRequest;
import com.phj.server.model.response.ServletResponse;
import com.phj.server.model.servlet.ServletConfig;

/**
 * 描述: 供子类从写的
 *       当http解析完成时，找到对应的Servlet，判断对应的method，执行对应的方法和返回
 * create: PHJ
 * time:2019/5/29 20:17
 */
public abstract class HttpServlet implements Servlet {

    private ServletConfig config;

    @Override
    public void init(ServletConfig var1) {
        this.config = var1;
    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    public void service(ServletRequest request, ServletResponse response) {
        this.doService(request, response);
    }

    private void doService(ServletRequest req, ServletResponse resp) {
        String method = req.method();
        long lastModified;
        // TODO: 2019/5/29 此部分简化了
        if (method.equals("GET")) {
            this.doGet(req, resp);
        } else if (method.equals("POST")) {
            this.doPost(req, resp);
        }
    }

    /**
     * 给子类重写的get请求
     *
     * @param req  ServletRequest
     * @param resp ServletResponse
     */
    protected void doGet(ServletRequest req, ServletResponse resp) {

    }

    /**
     * 给子类重写的post请求
     *
     * @param req  ServletRequest
     * @param resp ServletResponse
     */
    protected void doPost(ServletRequest req, ServletResponse resp) {

    }


    @Override
    public void destroy() {

    }
}
