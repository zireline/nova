package com.zireline.collinearpoints;

import org.junit.jupiter.api.Test;

import com.zireline.TestDataReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestDataReaderTest {

  @Test
  public void testReadFile() throws IOException {
    TestDataReader reader = new TestDataReader();

    List<String> columnNames = Arrays.asList("x", "y");
    Map<String, Object> data = reader.parseFile("src/test/resources/test-data/input8.txt", columnNames);

    System.out.println("Has override");
    // Log the size of the data
    for (Map.Entry<String, Object> entry : data.entrySet()) {
      System.out.println(entry.getKey() + ": " + entry.getValue());
    }

    // Check that the keys of the map are "x", "y", and "prevHeaders"
    Set<String> expectedKeys = new HashSet<>(Arrays.asList("x", "y", "prevHeaders"));
    assertTrue(data.keySet().containsAll(expectedKeys));
  }

  @Test
  public void testReadFileWithNullColumnName() throws IOException {
    TestDataReader reader = new TestDataReader();

    Map<String, Object> data = reader.parseFile("src/test/resources/test-data/input8.txt", null);

    System.out.println("No override");
    // Log the size of the data
    for (Map.Entry<String, Object> entry : data.entrySet()) {
      System.out.println(entry.getKey() + ": " + entry.getValue());
    }

    // Check that the keys of the map are "8" and "col1"
    Set<String> expectedKeys = new HashSet<>(Arrays.asList("8", "col1"));
    assertTrue(data.keySet().containsAll(expectedKeys));
  }
}