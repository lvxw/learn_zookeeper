package com.test

import org.I0Itec.zkclient.{IZkDataListener, ZkClient}
import org.apache.zookeeper.CreateMode

object ZkClientTest  extends  App {

  private val zkPath = "artemis-02:2181,artemis-03:2181,artemis-04:2181"

  private val zkClient = new ZkClient(zkPath)

  zkClient.createPersistent("/tmp/lvxw/aaa/bb",true)

  zkClient.subscribeDataChanges("/tmp/lvxw/aaa/bb",new IZkDataListener {
    override def handleDataChange(path: String, newData: Any): Unit = {
      println(path)
      println(newData)
    }

    override def handleDataDeleted(oldPath: String): Unit = {
      println(oldPath)
    }
  })

  zkClient.writeData("/tmp/lvxw/aaa/bb","123")
  Thread.sleep(1000)
  zkClient.delete("/tmp/lvxw/aaa/bb")


  Thread.sleep(100000)


  def createNode(path:String,data:Any,mode:CreateMode):String ={
    zkClient.create(path,data,mode)
  }

  def isExists(path:String):Boolean = {
    zkClient.exists(path)
  }

  def readData(path:String):Any ={
    zkClient.readData(path)
  }

  def deleteNaode(path:String): Boolean ={
    zkClient.delete(path)
  }


}
