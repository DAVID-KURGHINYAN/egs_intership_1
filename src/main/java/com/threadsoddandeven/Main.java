package com.threadsoddandeven;

import com.threadsoddandeven.detect.DetectThread;
import com.threadsoddandeven.even.EvenThread;
import com.threadsoddandeven.handler.HandlerLock;
import com.threadsoddandeven.odd.OddThread;

public class Main {
    public static void main(String[] args) {
        HandlerLock lock = new HandlerLock();
        EvenThread evenThread = new EvenThread(lock);
        OddThread oddThread = new OddThread(lock);
        DetectThread detectThread = new DetectThread(lock);

        evenThread.start();
        oddThread.start();
        detectThread.start();
    }
}
