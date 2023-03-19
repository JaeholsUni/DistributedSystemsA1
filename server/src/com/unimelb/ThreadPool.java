package com.unimelb;

import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {
    private final int threadNumber;
    private final ThreadWorker[] threads; //Worry about this later
    private final LinkedBlockingQueue<Runnable> queue;

    public ThreadPool(int threadNumber) {
        this.threadNumber = threadNumber;
        queue =  new LinkedBlockingQueue<>();
        threads = new ThreadWorker[threadNumber];

        for (int i = 0; i < threadNumber; i++) {
            threads[i] = new ThreadWorker(queue); //worry abt this in a minute
            threads[i].start();
        }
    }

    public void addTask(Runnable task) {
        synchronized (queue) {
            queue.add(task);
            queue.notify();
        }
    }


}
