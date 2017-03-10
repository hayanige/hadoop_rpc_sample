package com.hayanige.sample.hadoop.rpc;

import java.io.IOException;

public class SampleProtocolImpl implements SampleProtocol {
  public String echo(String str) throws IOException {
    return str;
  }

  public int add(int a, int b) throws IOException {
    return a + b;
  }
}
