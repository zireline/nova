package com.zireline.collinearpoints.display;

import com.zireline.collinearpoints.Drawable;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class GridPlane extends Pane {
  final int PADDING = 20;
  final int HEIGHT = 500;
  final int WIDTH = 500;

  public GridPlane() {

    Line verticalLine = new Line(0, HEIGHT, 0, 0);
    verticalLine.setStrokeWidth(2);
    verticalLine.setStroke(Color.BLACK);
    verticalLine.setTranslateX(PADDING); // add right padding
    verticalLine.setTranslateY(PADDING); // subtract up padding
    verticalLine.setTranslateY(HEIGHT);

    Line horizontalLine = new Line(0, 0, WIDTH, 0);
    horizontalLine.setStrokeWidth(2);
    horizontalLine.setStroke(Color.BLACK);
    horizontalLine.setTranslateX(PADDING); // add right padding
    horizontalLine.setTranslateY(PADDING); // subtract up padding
    horizontalLine.setTranslateY(HEIGHT);

    this.setRotate(-180);
    this.setScaleX(-1);

    this.getChildren().addAll(verticalLine, horizontalLine);
  }

  public void drawShape(Drawable drawable) {
    final Shape shape = drawable.draw();
    shape.setTranslateX(PADDING);
    shape.setTranslateY(PADDING);
    shape.setTranslateY(HEIGHT);
    this.getChildren().add(shape);
  }

  public void drawShape(Shape shape) {
    shape.setTranslateX(PADDING);
    shape.setTranslateY(PADDING);
    shape.setTranslateY(HEIGHT);
    this.getChildren().add(shape);
  }
}