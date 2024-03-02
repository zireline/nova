package com.zireline;

import com.zireline.collinearpoints.display.GridPlane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

  @SuppressWarnings("exports")
  @Override
  public void start(Stage stage) {
    GridPlane root = new GridPlane();

    var scene = new Scene(root, 500, 500);
    stage.setScene(scene);
    stage.show();

    // Do not modify
    long pid = ProcessIdRetriever.getPid(ProcessHandle.current());
    System.out.println("PID: " + pid);
  }

  public static void main(String[] args) {
    launch();
  }
}