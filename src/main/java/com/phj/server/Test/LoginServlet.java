package com.phj.server.Test;

import com.phj.server.handle.HttpServlet;
import com.phj.server.model.request.ServletRequest;
import com.phj.server.model.response.ServletResponse;
import com.phj.server.model.servlet.ServletConfig;
import com.phj.server.pretreatment.response.ResponseManager;

import java.util.Date;

import static com.phj.server.pretreatment.NetParameter.BLANK;
import static com.phj.server.pretreatment.NetParameter.CRLF;

public class LoginServlet extends HttpServlet {

    @Override
    public void init(ServletConfig var1) {
        System.out.println("LoginServlet init");
    }

    @Override
    protected void doGet(ServletRequest req, ServletResponse resp) {
        System.out.println("LoginServlet doGet ");
        //响应
        StringBuilder responseContext = new StringBuilder();
        responseContext.append("<html><head><title>HTTP响应示例</title>" +
                "</head><body>Hello world!蒲豪杰</body></html>");


        //1)  HTTP协议版本、状态代码、描述
        //2)  响应头(ServletResponse Head)
        //正文长度 ：字节长度
        //3)正文之前
        //4)正文

        String response = "HTTP/1.1" + BLANK + "200" + BLANK + "OK" + CRLF +
                "Server:ISimServer Server/0.0.1" + CRLF +
                "Date:" + new Date() + CRLF +
                "Content-type:text/html;charset=UTF-8" + CRLF +
                "Content-Length:" + responseContext.toString().getBytes().length + CRLF +
                CRLF +
                responseContext;
        ResponseManager.getInstance().response(resp, response.getBytes());
    }
}
