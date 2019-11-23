package com.learn.optimize.master_worker;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author WDS
 * @date 2019/11/22 18:41
 * Master-Worker模式的 Master部分
 */
public class Master {

    //任务队列
    protected Queue<Object> workQueue = new ConcurrentLinkedQueue<>();
    //Worker进程队列
    protected Map<String,Thread> threadMap = new HashMap<>();
    //子任务处理结果集
    protected Map<String,Object> resultMap = new ConcurrentHashMap<>();

    //是否所有的子任务都结束了
    public boolean isComplete(){
        for(Map.Entry<String,Thread> entry : threadMap.entrySet()){
            if(entry.getValue().getState() != Thread.State.TERMINATED){
                return false;
            }
        }
        return true;
    }

    //Master的构造，需要一个Worker进程逻辑，和需要的Worker进程数量
    public Master(Worker worker, int countWorker) {
        worker.setWorkerQueue(workQueue);
        worker.setResultMap(resultMap);
        for(int i=0; i<countWorker; i++){
            threadMap.put(Integer.toString(i),new Thread(worker,Integer.toString(i)));
        }
    }

    //提交一个任务
    public void submit(Object job){
        workQueue.add(job);
    }

    //返回子任务结果集
    public Map<String,Object> getResultMap(){
        return resultMap;
    }

    //开始运行所有的Worker进程，进行处理
    public void execute(){
        for(Map.Entry<String,Thread> entry : threadMap.entrySet()){
            entry.getValue().start();
        }
    }
}