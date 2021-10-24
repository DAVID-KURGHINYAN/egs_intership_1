package com.threadsoddandeven.handler;

public class HandlerLock {
    public static final int iterationLimit = 20;
    private int count;

    public void increment() {
        count++;
    }

    public int counter() {
        return count;
    }
}
