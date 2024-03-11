package com.zireline.collinearpoints.algorithm;

import java.util.Arrays;

public class Sorter {
  public static <T extends Comparable<T>> void sort(T[] array) {
    Arrays.sort(array);
  }
}