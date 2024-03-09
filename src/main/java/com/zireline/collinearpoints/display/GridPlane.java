package com.zireline.collinearpoints.display;

import com.zireline.collinearpoints.Drawable;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class GridPlane extends Pane {
  final int PADDING = 20;

  public GridPlane() {

    Line verticalLine = new Line(0, 0, 0, 500);
    verticalLine.setStrokeWidth(2);
    verticalLine.setStroke(Color.BLACK);
    verticalLine.setTranslateX(PADDING); // add right padding
    verticalLine.setTranslateY(PADDING); // add down padding

    Line horizontalLine = new Line(0, 500, 500, 500);
    horizontalLine.setStrokeWidth(2);
    horizontalLine.setStroke(Color.BLACK);
    horizontalLine.setTranslateX(PADDING); // add right padding
    horizontalLine.setTranslateY(PADDING); // add down padding

    Text label = new Text(0, 500, "(0,0)");
    label.setTranslateX(20); // add right padding
    label.setTranslateY(35); // add down padding

    this.getChildren().addAll(verticalLine, horizontalLine, label);
  }

  public void drawShape(Drawable drawable) {
    final Shape shape = drawable.draw();
    shape.setTranslateX(PADDING);
    shape.setTranslateY(PADDING);

    this.getChildren().add(shape);
  }

  public void drawShape(Shape shape) {
    shape.setTranslateX(PADDING);
    shape.setTranslateY(PADDING);
    this.getChildren().add(shape);
  }
}