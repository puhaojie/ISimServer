package com.phj.server.model.servlet;

public class ServletConfig {
    String servletName;

    public ServletConfig(String servletName) {
        this.servletName = servletName;
    }

    public String getServletName() {
        return servletName;
    }
}
