package com.zireline;

import com.zireline.collinearpoints.LineSegment;
import com.zireline.collinearpoints.Point;
import com.zireline.collinearpoints.display.GridPlane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class App extends Application {

  @SuppressWarnings("exports")
  @Override
  public void start(Stage stage) {
    GridPlane root = new GridPlane();

    // draw shapes here
    root.drawShape(new Point(250, 250));
    root.drawShape(new Point(250, 350));

    // draw line segment here
    root.drawShape(new LineSegment(new Point(250, 250), new Point(250, 350)));

    var scene = new Scene(root, 600, 550);
    stage.setScene(scene);

    // Get the screen width
    double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();

    // Set the stage x position to the right side of the screen
    stage.setX(screenWidth - scene.getWidth());

    stage.show();

    // Do not modify
    long pid = ProcessIdRetriever.getPid(ProcessHandle.current());
    System.out.println("PID: " + pid);
  }

  public static void main(String[] args) {
    launch();
  }
}