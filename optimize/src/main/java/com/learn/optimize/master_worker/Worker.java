package com.learn.optimize.master_worker;

import java.util.Map;
import java.util.Queue;

/**
 * @author WDS
 * @date 2019/11/22 20:34
 * Master-Worker模式的 Worker部分
 */
public class Worker implements Runnable {
    //任务队列，用于取得子任务
    protected Queue<Object> workerQueue;
    //子任务处理结果集
    protected Map<String,Object> resultMap;

    public void setWorkerQueue(Queue<Object> workerQueue) {
        this.workerQueue = workerQueue;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    //子任务处理的逻辑，在子类中实现具体逻辑
    public Object handle(Object input){
        return input;
    }

    @Override
    public void run() {
        while (true){
            Object input = workerQueue.poll();
            if(input == null) break;
            //处理子任务
            Object re = handle(input);
            resultMap.put(Integer.toString(input.hashCode()),re);
        }
    }
}