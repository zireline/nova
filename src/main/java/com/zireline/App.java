package com.zireline;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.prefs.Preferences;

import com.zireline.collinearpoints.BruteCollinearPoints;
import com.zireline.collinearpoints.LineSegment;
import com.zireline.collinearpoints.Point;
import com.zireline.collinearpoints.Renderer;
import com.zireline.collinearpoints.algorithm.FastCollinearPoints;
import com.zireline.collinearpoints.display.GridPlane;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Screen;
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

      List<String> filenames = reader.getFileNames("src/resources/test-data");

      ComboBox<String> fileComboBox = new ComboBox<>();
      fileComboBox.getItems().addAll(filenames);

      ComboBox<String> methodComboBox = new ComboBox<>();
      methodComboBox.setValue("Fast");
      methodComboBox.getItems().addAll("Brute Force", "Fast");

      fileComboBox.setOnAction(event -> {
        root.clearShapes();
        String selectedFile = fileComboBox.getSelectionModel().getSelectedItem();
        try {
          List<Point> points = readPointsFromFile("src/resources/test-data/" + selectedFile);
          String selectedMethod = methodComboBox.getSelectionModel().getSelectedItem();
          long startTime = System.nanoTime();

          if (selectedMethod.equals("Brute Force")) {
            BruteCollinearPoints collinearPoints = new BruteCollinearPoints(points);
            drawPoints(root, points, collinearPoints.segments());
          }

          if (selectedMethod.equals("Fast")) {
            FastCollinearPoints collinearPoints = new FastCollinearPoints(points);
            drawPoints(root, points, collinearPoints.segments());
          }

          long endTime = System.nanoTime();
          long duration = (endTime - startTime);

          System.out.println("Execution time in nanoseconds: " + duration);
          System.out.println("Execution time in milliseconds: " + duration / 1000000);
        } catch (IOException e) {
          System.out.println("ERROR READING FILE: " + e.getMessage());
        }
      });

      root.addComboBox(fileComboBox, 100);
      root.addComboBox(methodComboBox, 50);

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
    }

    return points;
  }

  private void drawPoints(GridPlane root, List<Point> points, List<LineSegment> segments) {
    Renderer.render(root, points, segments);
  }

  private void setScene(Stage stage, GridPlane root) {
    var scene = new Scene(root, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    stage.setScene(scene);
    System.out.println("Scene set");
  }

  private void setWindowProperties(Stage stage) {
    Preferences prefs = Preferences.userNodeForPackage(App.class);

    double x = prefs.getDouble(PREF_WINDOW_X, 0);
    double y = prefs.getDouble(PREF_WINDOW_Y, 0);
    double width = prefs.getDouble(PREF_WINDOW_WIDTH, 800); // default width
    double height = prefs.getDouble(PREF_WINDOW_HEIGHT, 600); // default height
    boolean maximized = prefs.getBoolean(PREF_WINDOW_MAXIMIZED, false);

    // Check if the saved window position is within the bounds of any screen
    boolean windowIsOnAScreen = Screen.getScreensForRectangle(x, y, width, height).size() > 0;

    if (!windowIsOnAScreen) {
      // If not, reset the window position to the primary screen's bounds
      Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
      x = primaryScreenBounds.getMinX();
      y = primaryScreenBounds.getMinY();
      width = primaryScreenBounds.getWidth();
      height = primaryScreenBounds.getHeight();
    }

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