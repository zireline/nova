package com.zireline.collinearpoints.algorithm;

import java.util.Arrays;
import java.util.Comparator;

public class Sorter {

  public static <T extends Comparable<T>> void mergeSort(T[] array) {
    mergeSort(array, Comparator.naturalOrder());
  }

  public static <T> void mergeSort(T[] array, Comparator<? super T> comparator) {
    if (array.length < 2) {
      return;
    }

    T[] left = Arrays.copyOfRange(array, 0, array.length / 2);
    T[] right = Arrays.copyOfRange(array, array.length / 2, array.length);

    mergeSort(left, comparator);
    mergeSort(right, comparator);

    merge(array, left, right, comparator);
  }

  private static <T> void merge(T[] result, T[] left, T[] right, Comparator<? super T> comparator) {
    int i = 0, leftIndex = 0, rightIndex = 0;

    while (leftIndex < left.length && rightIndex < right.length) {
      if (comparator.compare(left[leftIndex], right[rightIndex]) <= 0) {
        result[i++] = left[leftIndex++];
      } else {
        result[i++] = right[rightIndex++];
      }
    }

    while (leftIndex < left.length) {
      result[i++] = left[leftIndex++];
    }

    while (rightIndex < right.length) {
      result[i++] = right[rightIndex++];
    }
  }
}