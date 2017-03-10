package com.hayanige.sample.hadoop.rpc;

import java.io.IOException;

public interface SampleProtocol {
  public static final long versionID = 1L;

  String echo(String str) throws IOException;

  int add(int a, int b) throws IOException;
}
