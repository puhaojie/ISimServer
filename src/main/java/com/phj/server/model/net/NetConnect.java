package com.phj.server.model.net;

import com.phj.server.excetion.NullArgumentException;
import com.phj.server.util.StreamUtil;

import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

/**
 * 描述: 保存输入输出流
 * create: PHJ
 * time:2019/5/28 10:52
 */
public class NetConnect implements Closeable {

    // 代表着此次会话的标志
    private String uuid = UUID.randomUUID().toString();
    private InputStream mInputStream;
    private OutputStream mOutputStream;


    public NetConnect(InputStream mInputStream, OutputStream mOutputStream) {
        if (mInputStream == null || mOutputStream == null) {
            throw new NullArgumentException("NetConnect stream is null !!");
        }
        this.mInputStream = mInputStream;
        this.mOutputStream = mOutputStream;
    }

    public InputStream getInputStream() {
        return mInputStream;
    }


    public OutputStream getOutputStream() {
        return mOutputStream;
    }


    /**
     *  关闭输入流
     */
    public void closeInputStream() {
        StreamUtil.close(mInputStream);
    }

    /**
     * 关闭输出流
     */
    public void closeOutputStream() {
        StreamUtil.close(mOutputStream);
    }

    @Override
    public void close() {
        StreamUtil.close(mInputStream);
        StreamUtil.close(mInputStream);
    }

    @Override
    public String toString() {
        return "NetConnect{" +
                "uuid='" + uuid + '\'' +
                '}';
    }
}
