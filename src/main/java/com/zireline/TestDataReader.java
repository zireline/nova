package com.zireline;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class TestDataReader {

  public Map<String, Object> parseFile(String filePath, List<String> providedColumnNames) throws IOException {
    Path path = Paths.get(filePath);
    BufferedReader fileReader = Files.newBufferedReader(path);
    BufferedReader cleanFileReader = Files.newBufferedReader(path);

    try (fileReader; cleanFileReader) {
      String headerLine = fileReader.readLine();
      String secondLine = fileReader.readLine();
      if (headerLine != null && secondLine != null) {
        String[] headerValues = headerLine.trim().split("\\s+");
        String[] dataValues = secondLine.trim().split("\\s+");
        ArrayList<String> extractedColumnNames = new ArrayList<String>();
        Map<String, Object> columnDataMap;

        for (int i = 0; i < dataValues.length; i++) {
          if (i == 0 && headerValues[i] != null && !headerValues[i].isEmpty()) {
            extractedColumnNames.add(headerValues[i]);
          } else {
            extractedColumnNames.add("col" + (i));
          }
        }

        if (providedColumnNames == null) {
          columnDataMap = initializeColumns(extractedColumnNames);
          populateColumns(cleanFileReader, columnDataMap, extractedColumnNames);

        } else {
          columnDataMap = initializeColumns(providedColumnNames);
          columnDataMap.put("prevHeaders", extractedColumnNames);
          populateColumns(cleanFileReader, columnDataMap, providedColumnNames);
        }

        return columnDataMap;
      } else {
        throw new IOException("File is empty");
      }
    }
  }

  private Map<String, Object> initializeColumns(List<String> columnNames) {
    Map<String, Object> parsedValues = new LinkedHashMap<>();
    for (String columnName : columnNames) {
      parsedValues.put(columnName, new ArrayList<>());
    }
    return parsedValues;
  }

  private void populateColumns(BufferedReader br, Map<String, Object> parsedValues, List<String> columnNames)
      throws IOException {
    // Skip the first line
    br.readLine();

    String line;
    while ((line = br.readLine()) != null) {
      String[] values = line.trim().split("\\s+");
      addValuesToColumns(values, columnNames, parsedValues);
    }
  }

  private void addValuesToColumns(String[] values, List<String> columnNames, Map<String, Object> parsedValues) {
    int minSize = Math.min(values.length, columnNames.size());
    for (int i = 0; i < minSize; i++) {
      addValueToColumn(values[i], columnNames.get(i), parsedValues);
    }
  }

  @SuppressWarnings("unchecked")
  private void addValueToColumn(String value, String columnName, Map<String, Object> parsedValues) {
    Object columnData = parsedValues.get(columnName);
    if (columnData instanceof List<?>) {
      List<Integer> columnDataList = (List<Integer>) columnData;
      columnDataList.add(Integer.valueOf(value));
    }
  }
}