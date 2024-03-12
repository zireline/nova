package com.zireline.collinearpoints.display;

import java.util.ArrayList;
import java.util.List;

import com.zireline.collinearpoints.Drawable;

import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class GridPlane extends Pane {
  private List<Shape> shapes = new ArrayList<>();
  final int PADDING = 20;
  final int HEIGHT = 500;
  final int WIDTH = 500;

  public GridPlane() {

    Line verticalLine = new Line(0, HEIGHT, 0, 0);
    verticalLine.setStrokeWidth(2);
    verticalLine.setStroke(Color.RED);
    verticalLine.setTranslateX(PADDING); // add right padding
    verticalLine.setTranslateY(PADDING); // subtract up padding
    verticalLine.setTranslateY(HEIGHT);

    Line horizontalLine = new Line(0, 0, WIDTH, 0);
    horizontalLine.setStrokeWidth(2);
    horizontalLine.setStroke(Color.RED);
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
    shapes.add(shape);
  }

  public void drawShape(Shape shape) {
    shape.setTranslateX(PADDING);
    shape.setTranslateY(PADDING);
    shape.setTranslateY(HEIGHT);
    this.getChildren().add(shape);
    shapes.add(shape);
  }

  public void clearShapes() {
    this.getChildren().removeAll(shapes);
    shapes.clear();
  }

  public void addComboBox(ComboBox<String> comboBox, int padding) {
    comboBox.setTranslateX(PADDING);
    comboBox.setTranslateY(PADDING);
    comboBox.setTranslateY(HEIGHT - padding);
    comboBox.setScaleX(-1);
    comboBox.setRotate(-180);

    this.getChildren().add(comboBox);
  }
}