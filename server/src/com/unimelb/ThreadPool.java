/*
Threadpool for connection threads

Distributed Systems Assignment 1
James Hollingsworth - 915178
jameswh@iinet.net.au
 */

package com.unimelb;

import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {
    private final ThreadWorker[] threads;
    private final LinkedBlockingQueue<Runnable> queue;

    public ThreadPool(int threadNumber) {
        queue =  new LinkedBlockingQueue<>();
        threads = new ThreadWorker[threadNumber];

        for (int i = 0; i < threadNumber; i++) {
            threads[i] = new ThreadWorker(queue);
            threads[i].start();
        }
    }

    public void addTask(Runnable task) {
            queue.add(task);
    }
}
