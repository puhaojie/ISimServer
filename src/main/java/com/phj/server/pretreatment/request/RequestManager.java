package com.phj.server.pretreatment.request;

import com.phj.server.Factory;
import com.phj.server.dispatcher.SimpleDispatcher;
import com.phj.server.model.net.NetConnect;
import com.phj.server.model.request.Headers;
import com.phj.server.model.request.HttpUrl;
import com.phj.server.model.request.RequestBody;
import com.phj.server.model.request.ServletRequest;
import com.phj.server.model.response.ServletResponse;
import com.phj.server.util.StreamUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 描述: 解析网络流的参数的管理者（调度者）
 * create: PHJ
 * time:2019/5/28 11:15
 */

public class RequestManager {

    public static RequestManager instance;
    // 资源锁
    private Lock mLock = new ReentrantLock();
    // 线程不安全
    private ArrayList<NetConnect> modelList = new ArrayList<>();

    // 单例模式
    private static class InnerClass {
        private static RequestManager single = new RequestManager();
    }

    static {
        instance = InnerClass.single;
    }


    public void insertModel(NetConnect netStreamModel) {
        if (netStreamModel == null)
            return;
        try {
            mLock.lock();
            modelList.add(netStreamModel);
        } finally {
            mLock.unlock();
        }

        executeAnalysis();
    }

    /**
     *  执行解析的数据
     */
    private void executeAnalysis() {
        Factory.runOnAsync(analysisRunnable);
    }

    private final Runnable analysisRunnable = () -> {
        NetConnect streamModel;
        try {
            mLock.lock();
            if (modelList.size() <= 0) {
                return;
            }
            // 得到首
            streamModel = modelList.remove(0);
        } finally {
            mLock.unlock();
        }

        realAnalysis(streamModel);
    };


    private void realAnalysis(NetConnect streamModel) {
        byte[] data = new byte[20480];
        int len = 0;
        try {
            len = streamModel.getInputStream().read(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (len < 0) {
            System.out.println("当前读取异常 len = "+len);
            streamModel.close();
            return;
        }
        System.out.println("读取成功 len = " + len);
        //接收客户端的请求信息
        String requestInfo = new String(data, 0, len).trim();
        // 尝试继续调度一次
        executeAnalysis();

        ServletRequest servletRequest = analysisRequest(requestInfo);
        ServletResponse servletResponse = new ServletResponse(streamModel.getInputStream(),streamModel.getOutputStream());
        SimpleDispatcher.dispatcher(servletRequest,servletResponse);

    }

    // TODO: 2019/6/14 用于解析出
    private ServletRequest analysisRequest(String requestInfo) {
        HttpUrl url = new HttpUrl("http","127.0.0.1",8080,"/login");
        String method = "GET";
        Headers headers = new Headers();
        RequestBody body = new RequestBody();
        return new ServletRequest(url,method,headers,body);
    }

}
