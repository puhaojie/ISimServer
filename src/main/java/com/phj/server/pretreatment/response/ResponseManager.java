package com.phj.server.pretreatment.response;

import com.phj.server.model.net.NetConnect;

import java.io.IOException;

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


    public void response(NetConnect streamModel, byte[] bytes) {
        if (streamModel == null || bytes == null || bytes.length <= 0)
            return;
        try {
            streamModel.getOutputStream().write(bytes);
            streamModel.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            streamModel.close();
        }

    }

}
