package com.phj.server.pretreatment.response;

import com.phj.server.model.net.NetConnect;
import com.phj.server.model.response.ServletResponse;
import com.phj.server.util.StreamUtil;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 描述: 返回封装的管理
 * create: PHJ
 * time:2019/5/28 11:36
 */
public class ResponseManager {

    private static ResponseManager instance;

    static {
        instance = new ResponseManager();
    }

    public static ResponseManager getInstance() {
        return instance;
    }


    public void response(ServletResponse servletResponse, byte[] bytes) {
        if (servletResponse == null || servletResponse.getOutputStream() == null || bytes == null || bytes.length <= 0)
            return;
        try {
            servletResponse.getOutputStream().write(bytes);
            servletResponse.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            StreamUtil.close(servletResponse);
        }

    }

}
