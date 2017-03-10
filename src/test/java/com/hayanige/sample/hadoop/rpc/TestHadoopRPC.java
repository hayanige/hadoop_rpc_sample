package com.hayanige.sample.hadoop.rpc;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.InetSocketAddress;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.Server;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestHadoopRPC {
  private static Server server;  // server side
  private static SampleProtocol proxy; // client side

  @BeforeClass
  public static void setup() throws IOException {
    Configuration conf = new Configuration();

    // server side setting
    server = new RPC.Builder(conf).setProtocol(SampleProtocol.class)
        .setInstance(new SampleProtocolImpl()).setBindAddress("localhost")
        .setPort(9000).setNumHandlers(3).build();
    server.start();

    // client side setting
    InetSocketAddress address = new InetSocketAddress("localhost", 9000);
    proxy = RPC.getProxy(SampleProtocol.class, SampleProtocol.versionID,
        address, conf);
  }

  @Test
  public void testEcho() throws IOException {
    assertEquals("Hadoop RPC!!", proxy.echo("Hadoop RPC!!"));
  }

  @Test
  public void testAdd() throws IOException {
    assertEquals(15, proxy.add(7, 8));
  }
}
