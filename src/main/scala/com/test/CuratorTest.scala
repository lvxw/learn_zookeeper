package com.test

import org.apache.curator.framework.{CuratorFramework, CuratorFrameworkFactory}
import org.apache.curator.retry.ExponentialBackoffRetry

object CuratorTest extends App{
  private val zkPath = "artemis-02:2181,artemis-03:2181,artemis-04:2181"


  private val client: CuratorFramework = CuratorFrameworkFactory.newClient(zkPath, new ExponentialBackoffRetry(1000,3))
  client.start()

  client.create().forPath("/tmp/lvxw/a","hello word".getBytes())

  client

  client.close()

}
