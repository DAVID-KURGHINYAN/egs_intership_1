package com.threadsoddandeven.detect;

import com.threadsoddandeven.handler.HandlerLock;

public class DetectThread extends Thread {
    private final HandlerLock handlerLock;

    public DetectThread(HandlerLock lock) {
        this.handlerLock = lock;
    }

    @Override
    public void run() {
        synchronized (handlerLock) {
            while (handlerLock.counter() <= HandlerLock.iterationLimit) {
                for (long n = 2; n <= 20; n++) {
                    for (long i = 1; i < n; i++) {
                        if (n % i == 0) {
                            System.out.println("Detect Thread: " + handlerLock.counter());
                        }
                        handlerLock.increment();
                    }
                    handlerLock.notifyAll();
                    try {
                        handlerLock.wait();
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (handlerLock.counter() > HandlerLock.iterationLimit) {
                        System.exit(0);
                    }
                }
            }
        }
    }
}
