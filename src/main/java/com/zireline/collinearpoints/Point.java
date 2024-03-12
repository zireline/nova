package com.zireline.collinearpoints;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import java.util.Comparator;

public class Point implements Drawable, Comparable<Point> {
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
      return +0.0; // Ensure positive zero
    }

    return (that.y - this.y) / (that.x - this.x);
  }

  @Override
  public Shape draw() {
    Circle circle = new Circle(this.x, this.y, 0.5);
    circle.setFill(Color.BLUE);
    return circle;
  }

  @Override
  public Shape drawTo(Point that) {
    Line line = new Line(x, this.y, that.getX(), that.getY());
    line.setStroke(Color.RED);
    line.setStrokeWidth(2);
    return line;
  }

  @Override
  public String toString() {
    return "(" + x + ", " + y + ")";
  }

  public void setX(double x) {
    this.x = x;
  }

  public void setY(double y) {
    this.y = y;
  }

  // compare two points by y-coordinates, breaking ties by x-coordinates
  @Override
  public int compareTo(Point that) {
    if (this.y < that.y)
      return -1;
    if (this.y > that.y)
      return +1;
    if (this.x < that.x)
      return -1;
    if (this.x > that.x)
      return +1;
    return 0;
  }

  // compare two points by the slopes they make with this point
  public Comparator<Point> slopeOrder() {
    return Comparator.comparingDouble(this::slopeTo);
  }
}
