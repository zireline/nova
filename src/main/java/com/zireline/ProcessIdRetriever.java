package com.zireline;

import java.lang.ProcessHandle;

public class ProcessIdRetriever {
  public static long getPid(ProcessHandle processHandle) {
    return processHandle.pid();
  }
}