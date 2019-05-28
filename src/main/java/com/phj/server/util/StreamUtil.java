package com.phj.server.util;


import java.io.Closeable;
import java.io.IOException;

/**
 * 对文件流的操作工具类
 *
 * @author phj
 *         created at 2018/3/2 17:19
 */

public class StreamUtil {



    /**
     * 对流进行close操作
     *
     * @param closeables Closeable
     */
    public static void close(Closeable... closeables) {
        if (closeables == null)
            return;
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}