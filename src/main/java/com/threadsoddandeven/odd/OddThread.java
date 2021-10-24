package com.threadsoddandeven.odd;

import com.threadsoddandeven.handler.HandlerLock;

public class OddThread extends Thread{

    private final HandlerLock handlerLock;

    public OddThread(HandlerLock lock) {
        this.handlerLock = lock;
    }
    @Override
    public void run(){
        synchronized (handlerLock) {
            while (handlerLock.counter() <= HandlerLock.iterationLimit) {
                if (handlerLock.counter() % 2 != 0 ) {
                    System.out.println("Odd Thread: " + handlerLock.counter());
                    handlerLock.increment();
                }
                handlerLock.notifyAll();
                try {
                    handlerLock.wait();
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (handlerLock.counter()>HandlerLock.iterationLimit){
                    System.exit(0);
                }
            }
        }

    }
}
