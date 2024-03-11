package com.zireline;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.prefs.Preferences;

import com.zireline.collinearpoints.LineSegment;
import com.zireline.collinearpoints.Point;
import com.zireline.collinearpoints.Renderer;
import com.zireline.collinearpoints.algorithm.Enums;
import com.zireline.collinearpoints.display.GridPlane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

  private static final String PREF_WINDOW_X = "windowX";
  private static final String PREF_WINDOW_Y = "windowY";
  private static final String PREF_WINDOW_WIDTH = "windowWidth";
  private static final String PREF_WINDOW_HEIGHT = "windowHeight";
  private static final String PREF_WINDOW_MAXIMIZED = "windowMaximized";

  private static final double DEFAULT_X = 10;
  private static final double DEFAULT_Y = 10;
  private static final double DEFAULT_WIDTH = 600;
  private static final double DEFAULT_HEIGHT = 550;
  private static final TestDataReader reader = new TestDataReader();

  @SuppressWarnings({ "exports" })
  @Override
  public void start(Stage stage) {
    try {
      GridPlane root = new GridPlane();
      List<Point> points = readPointsFromFile("src/resources/test-data/rs1423.txt");
      drawPoints(root, points);
      setScene(stage, root);
    } catch (IOException e) {
      System.out.println("ERROR READING FILE: " + e.getMessage());
    } catch (Exception e) {
      System.out.println("EXCEPTION: " + e.getMessage());
    } catch (Error e) {
      System.out.println("ERROR: " + e.getMessage());
    }

    setWindowProperties(stage);
    stage.show();
    storeWindowPropertiesOnClose(stage);

    long pid = ProcessIdRetriever.getPid(ProcessHandle.current());
    System.out.println("PID: " + pid);
  }

  @SuppressWarnings({ "unchecked" })
  private List<Point> readPointsFromFile(String filePath) throws IOException {
    List<String> columnNames = Arrays.asList("x", "y");
    Map<String, Object> data = reader.parseFile(filePath, columnNames);
    System.out.println("Data parsed from file: " + data);

    int length = Integer.parseInt(((List<String>) data.get("prevHeaders")).get(0));
    System.out.println("Length of data: " + length);

    List<Point> points = new ArrayList<>();
    for (int i = 0; i < length; i++) {
      int pointX = ((List<Integer>) data.get("x")).get(i);
      int pointY = ((List<Integer>) data.get("y")).get(i);

      points.add(new Point(pointX, pointY));
      System.out.println("Added point: (" + pointX + ", " + pointY + ")");
    }

    return points;
  }

  private void drawPoints(GridPlane root, List<Point> points) {

    Renderer.render(root, points);
  }

  private void setScene(Stage stage, GridPlane root) {
    var scene = new Scene(root, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    stage.setScene(scene);
    System.out.println("Scene set");
  }

  private void setWindowProperties(Stage stage) {
    Preferences prefs = Preferences.userNodeForPackage(App.class);
    double x = prefs.getDouble(PREF_WINDOW_X, DEFAULT_X);
    double y = prefs.getDouble(PREF_WINDOW_Y, DEFAULT_Y);
    double width = prefs.getDouble(PREF_WINDOW_WIDTH, DEFAULT_WIDTH);
    double height = prefs.getDouble(PREF_WINDOW_HEIGHT, DEFAULT_HEIGHT);
    boolean maximized = prefs.getBoolean(PREF_WINDOW_MAXIMIZED, false);

    stage.setX(x);
    stage.setY(y);
    stage.setWidth(width);
    stage.setHeight(height);
    stage.setMaximized(maximized);
  }

  private void storeWindowPropertiesOnClose(Stage stage) {
    stage.setOnCloseRequest(event -> {
      Preferences prefs = Preferences.userNodeForPackage(App.class);
      prefs.putDouble(PREF_WINDOW_X, stage.getX());
      prefs.putDouble(PREF_WINDOW_Y, stage.getY());
      prefs.putDouble(PREF_WINDOW_WIDTH, stage.getWidth());
      prefs.putDouble(PREF_WINDOW_HEIGHT, stage.getHeight());
      prefs.putBoolean(PREF_WINDOW_MAXIMIZED, stage.isMaximized());
    });
  }

  public static void main(String[] args) {
    launch();
  }
}