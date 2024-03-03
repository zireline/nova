package com.zireline.collinearpoints;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;

import com.google.gson.Gson;

public class JsonReader {

  private Gson gson;

  public JsonReader() {
    this.gson = new Gson();
  }

  public <T> T readJson(String filePath, Type typeOfT) throws IOException {
    try (FileReader reader = new FileReader(filePath)) {
      return gson.fromJson(reader, typeOfT);
    }
  }
}