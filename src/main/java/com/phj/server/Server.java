package com.phj.server;

import com.phj.server.model.net.NetStreamModel;
import com.phj.server.pretreatment.request.RequestManager;

import java.io.*;
import java.net.*;
import java.util.Date;

public class Server implements Runnable{
    private ServerSocket server;
    private final static int LISTEN_PORT = 8080;
    //换行控制符号
    public static final String CRLF = "\r\n";
    public static final String BLANK = " ";

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
        for (; ; ) {
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
            NetStreamModel streamModel = new NetStreamModel(client.getInputStream(),client.getOutputStream());
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

    }


}
