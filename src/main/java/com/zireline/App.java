package com.zireline;

import java.util.prefs.Preferences;

import com.zireline.collinearpoints.LineSegment;
import com.zireline.collinearpoints.Point;
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

  @Override
  public void start(Stage stage) {
    GridPlane root = new GridPlane();

    // draw shapes here
    root.drawShape(new Point(250, 250));
    root.drawShape(new Point(250, 350));

    // draw line segment here
    root.drawShape(new LineSegment(new Point(250, 250), new Point(250, 350)));

    var scene = new Scene(root, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    stage.setScene(scene);

    // Get the stored properties of the window
    Preferences prefs = Preferences.userNodeForPackage(App.class);
    double x = prefs.getDouble(PREF_WINDOW_X, DEFAULT_X);
    double y = prefs.getDouble(PREF_WINDOW_Y, DEFAULT_Y);
    double width = prefs.getDouble(PREF_WINDOW_WIDTH, DEFAULT_WIDTH);
    double height = prefs.getDouble(PREF_WINDOW_HEIGHT, DEFAULT_HEIGHT);
    boolean maximized = prefs.getBoolean(PREF_WINDOW_MAXIMIZED, false);

    // Set the properties of the window
    stage.setX(x);
    stage.setY(y);
    stage.setWidth(width);
    stage.setHeight(height);
    stage.setMaximized(maximized);

    stage.show();

    // Store the properties of the window when it is closed
    stage.setOnCloseRequest(event -> {
      prefs.putDouble(PREF_WINDOW_X, stage.getX());
      prefs.putDouble(PREF_WINDOW_Y, stage.getY());
      prefs.putDouble(PREF_WINDOW_WIDTH, stage.getWidth());
      prefs.putDouble(PREF_WINDOW_HEIGHT, stage.getHeight());
      prefs.putBoolean(PREF_WINDOW_MAXIMIZED, stage.isMaximized());
    });

    // Do not modify
    long pid = ProcessIdRetriever.getPid(ProcessHandle.current());
    System.out.println("PID: " + pid);
  }

  public static void main(String[] args) {
    launch();
  }
}