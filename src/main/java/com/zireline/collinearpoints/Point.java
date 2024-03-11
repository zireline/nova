package com.zireline.collinearpoints;

import com.zireline.collinearpoints.algorithm.Enums;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class Point implements Drawable {
  private double x;
  private double y;

  public Point() {
    // default constructor
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
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

    System.out.println("SLOPING: " + this + "/" + that);

    return (that.y - this.y) / (that.x - this.x);
  }

  @Override
  public Shape draw() {
    System.out.println("Drawing: (" + this.x + ", " + (500 - this.y) + ")");
    Circle circle = new Circle(this.x, 500 - this.y, 3);
    circle.setFill(Color.BLUE);
    return circle;
  }

  @Override
  public Shape drawTo(Point that) {
    Line line = new Line(x, 500 - this.y, that.getX(), 500 - that.getY());
    line.setStroke(Color.RED);
    line.setStrokeWidth(2);
    return line;
  }

  @Override
  public String toString() {
    return "(" + x + ", " + y + ")";
  }
}
