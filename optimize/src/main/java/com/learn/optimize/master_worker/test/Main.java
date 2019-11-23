package com.learn.optimize.master_worker.test;

import com.learn.optimize.master_worker.Master;

import java.util.Map;
import java.util.Set;

/**
 * @author WDS
 * @date 2019/11/22 20:57
 * 实现计算立方和的应用，并计算1~100的立方和，1³+2³+...+100³。
 */
public class Main {

    public static void main(String[] args) {
        //固定使用5个线程，并指定Worker
        Master master = new Master(new PlusWorker(),5);
        for (int i=0; i<100; i++)
            //提交100个子任务
            master.submit(i);
        //开始计算
        master.execute();
        //最终计算结果保存于此
        int re=0;
        Map<String,Object> resultMap = master.getResultMap();
        while (resultMap.size()>0 || !master.isComplete()){
            //不需要等待所有Worker都执行完，即可开始计算最终结果
            Set<String> keys = resultMap.keySet();
            String key = null;
            for (String k:keys){
                key = k;
                break;
            }
            Integer i = null;
            if(key != null)
                i = (Integer)resultMap.get(key);
            if(i != null)
                //最终结果
                re+=i;
            if(key != null)
                //移除已经被计算过的项
                resultMap.remove(key);
        }
        System.out.println(re);
    }
}