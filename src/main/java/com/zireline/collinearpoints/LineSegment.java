package com.zireline.collinearpoints;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class LineSegment implements Drawable {
  Point p;
  Point q;

  public LineSegment(Point p, Point q) {
    this.p = p;
    this.q = q;
  }

  @Override
  public Line draw() {
    Line line = new Line(p.getX(), p.getY(), q.getX(), q.getY());
    line.setStroke(Color.BLACK);
    line.setStrokeWidth(2);
    return line;
  }

  @Override
  public Shape drawTo(Point that) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'drawTo'");
  }

  @Override
  public String toString() {
    return "LineSegment [" + p.toString() + " -> " + q.toString() + "]";
  }
}
