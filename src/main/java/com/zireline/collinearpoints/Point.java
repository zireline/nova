package com.zireline.collinearpoints;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class Point implements Drawable {
  double x;
  double y;

  public Point() {
    // default constructor
  }

  public double getX() {
    return x;
  }

  public void setX(double x) {
    this.x = x;
  }

  public double getY() {
    return y;
  }

  public void setY(double y) {
    this.y = y;
  }

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double slopeTo(Point that) {
    if (this.x == that.x) {
      if (this.y == that.y) {
        return Double.NEGATIVE_INFINITY;
      }
      return Double.POSITIVE_INFINITY;
    }
    if (this.y == that.y) {
      return 0;
    }
    return (that.y - this.y) / (that.x - this.x);
  }

  @Override
  public Shape draw() {
    Circle circle = new Circle(this.x, this.y, 4);
    circle.setFill(Color.BLACK);
    return circle;
  }
}
