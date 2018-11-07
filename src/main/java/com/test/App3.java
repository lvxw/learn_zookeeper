package com.test;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

public class App3 {
    public static String zkPath = "artemis-02:2181,artemis-03:2181,artemis-04:2181";
    public static ZkClient zkClient = new ZkClient(zkPath);

    public static void main(String[] args) throws InterruptedException {
        zkClient.create("/tmp/lvxw/aaa","", CreateMode.PERSISTENT);

        zkClient.subscribeDataChanges("/tmp/lvxw/aaa",new IZkDataListener() {
            @Override
            public void handleDataChange(String path, Object newData){
                System.out.println(123);
                String tmp = zkClient.readData(path);
                System.out.println(tmp);
                System.out.println(newData);
            }

            @Override
            public void handleDataDeleted(String oldPath){
                System.out.println(oldPath);
            }
        });

        zkClient.writeData("/tmp/lvxw/aaa","bbb");
        Thread.sleep(1000);
        zkClient.delete("/tmp/lvxw/aaa");

        Thread.sleep(10000);
    }
}
