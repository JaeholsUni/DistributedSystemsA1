package com.unimelb;

import java.util.concurrent.LinkedBlockingQueue;

public class ThreadWorker extends Thread {
    private final LinkedBlockingQueue<Runnable> queue;

    public ThreadWorker(LinkedBlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Runnable task;

        while (true) {
                try {
                    task = queue.take();
                    task.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        }
    }
}
