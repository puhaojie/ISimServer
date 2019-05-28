package com.phj.server.dispatcher;

import com.phj.server.model.net.NetStreamModel;
import com.phj.server.pretreatment.response.ResponseManager;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;

import static com.phj.server.pretreatment.NetParameter.BLANK;
import static com.phj.server.pretreatment.NetParameter.CRLF;

public class SimpleDispatcher {

    public static void dispatcher(String requestContent, NetStreamModel streamModel){

        System.out.println(requestContent);
        //响应
        StringBuilder responseContext = new StringBuilder();
        responseContext.append("<html><head><title>HTTP响应示例</title>" +
                "</head><body>Hello world!</body></html>");



        StringBuilder response = new StringBuilder();
        //1)  HTTP协议版本、状态代码、描述
        response.append("HTTP/1.1").append(BLANK).append("200").append(BLANK).append("OK").append(CRLF);
        //2)  响应头(Response Head)
        response.append("Server:ISimServer Server/0.0.1").append(CRLF);
        response.append("Date:").append(new Date()).append(CRLF);
        response.append("Content-type:text/html;charset=GBK").append(CRLF);
        //正文长度 ：字节长度
        response.append("Content-Length:").append(responseContext.toString().getBytes().length).append(CRLF);
        //3)正文之前
        response.append(CRLF);
        //4)正文
        response.append(responseContext);


        ResponseManager.getInstance().response(streamModel,response.toString().getBytes());

    }

}
