package com.threadsoddandeven.manager;

import java.util.List;

public class ThreadManager {
    private List<ThreadCallBack> callBackList;
    public ThreadManager addDetectThread(ThreadCallBack callBack){
        callBackList.add(callBack);
        return this;
    }

}
