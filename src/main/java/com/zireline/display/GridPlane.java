package com.zireline.display;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class GridPlane extends Pane {

  public GridPlane() {
    // Create the grid
    Circle[][] circles = new Circle[10][10];
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        // Create a circle (dot) at the center of each cell
        Circle circle = new Circle(i * 50 + 25, j * 50 + 25, 5);
        circles[i][j] = circle;
        getChildren().add(circle);
      }
    }

    // Connect some dots
    Line line = new Line(circles[0][0].getCenterX(), circles[0][0].getCenterY(), circles[9][9].getCenterX(),
        circles[9][9].getCenterY());
    getChildren().add(line);
  }
}
