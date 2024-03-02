package com.zireline.collinearpoints;

import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonReaderTest {

  private JsonReader jsonReader;
  private String filePath = "src/test/resources/test.json";

  @BeforeEach
  public void setUp() {
    jsonReader = new JsonReader();
  }

  @Test
  public void testReadJson() throws IOException {
    Type typeOfT = new TypeToken<Map<String, String>>() {
    }.getType();

    Map<String, String> result = jsonReader.readJson(filePath, typeOfT);

    assertEquals("testValue", result.get("testKey"));
  }

}