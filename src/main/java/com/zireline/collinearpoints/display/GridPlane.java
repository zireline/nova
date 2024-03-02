package com.zireline.collinearpoints.display;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class GridPlane extends Pane {

  public GridPlane() {
    Circle[][] gridCircles = new Circle[10][10];

    for (int rowIndex = 0; rowIndex < 10; rowIndex++) {
      for (int columnIndex = 0; columnIndex < 10; columnIndex++) {
        // Create a circle (dot) at the center of each cell
        Circle gridCircle = new Circle(rowIndex * 50 + 25, columnIndex * 50 + 25, 5);
        gridCircles[rowIndex][columnIndex] = gridCircle;
        getChildren().add(gridCircle);
      }
    }

    // Connect some dots
    Line diagonalLine = new Line(
        gridCircles[0][0].getCenterX(),
        gridCircles[0][0].getCenterY(),
        gridCircles[9][9].getCenterX(),
        gridCircles[9][9].getCenterY());
    getChildren().add(diagonalLine);
  }
}