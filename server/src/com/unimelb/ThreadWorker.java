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
            synchronized (queue) {
                while (queue.isEmpty()) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        System.out.println("Interrupted Exception");
                    }
                }

                task = queue.poll();

                try {
                    task.run();
                } catch (RuntimeException e) {
                    System.out.println("Runtime Exception");
                }
            }
        }
    }
}
