package com.phj.server;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 描述: 逻辑调度Factory
 * create: PHJ
 * time:2019/5/28 10:36
 */
public class Factory {

    // 单例模式
    private static final Factory instance;
    // 全局的线程池
    private final Executor executor;


    static {
        instance = new Factory();
    }

    private Factory() {
        // 新建一个4个线程的线程池
        executor = Executors.newFixedThreadPool(4);
    }

    /**
     * 异步运行的方法
     *
     * @param runnable Runnable
     */
    public static void runOnAsync(Runnable runnable) {
        // 拿到单例，拿到线程池，然后异步执行
        instance.executor.execute(runnable);
    }
}
