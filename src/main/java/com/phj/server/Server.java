package com.phj.server;

import com.phj.server.model.net.NetConnect;
import com.phj.server.pretreatment.request.RequestManager;

import java.io.*;
import java.net.*;

public class Server implements Runnable{
    private ServerSocket server;
    private final static int LISTEN_PORT = 8080;
    private volatile boolean isStop = false;

    /**
     * @param args
     */
    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    /**
     * 启动方法
     */
    private void start() {
        try {
            server = new ServerSocket(LISTEN_PORT);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        new Thread(this).start();


    }


    @Override
    public void run() {
        // 轮询监听
        while (!isStop) {
            receive();
        }
    }


    /**
     * 接收客户端
     */
    private void receive() {
        try {
            Socket client = server.accept();
            // 1.得到数据流
            NetConnect streamModel = new NetConnect(client.getInputStream(),client.getOutputStream());
            // 2、继续分发处理
            RequestManager.instance.insertModel(streamModel);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 听着服务器
     */
    public void stop() {
        isStop = true;
    }


}
