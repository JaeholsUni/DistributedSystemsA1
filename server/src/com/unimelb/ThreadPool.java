package com.unimelb;

import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {
    private final int threadNumber;
    private final ThreadWorker[] threads;
    private final LinkedBlockingQueue<Runnable> queue;

    public ThreadPool(int threadNumber) {
        this.threadNumber = threadNumber;
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

    public int getEnquedItems() {
        return queue.size();
    }
}
