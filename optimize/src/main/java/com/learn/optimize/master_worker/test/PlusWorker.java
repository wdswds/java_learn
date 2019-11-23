package com.learn.optimize.master_worker.test;

import com.learn.optimize.master_worker.Worker;

/**
 * @author WDS
 * @date 2019/11/22 20:55
 */
public class PlusWorker extends Worker {

    /**
     * Worker，求立方和
     * @param input
     * @return
     */
    @Override
    public Object handle(Object input) {
        Integer i = (Integer)input;
        return i*i*i;
    }
}