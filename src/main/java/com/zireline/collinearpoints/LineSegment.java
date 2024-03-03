package com.zireline.collinearpoints;

import javafx.scene.effect.Light.Point;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class LineSegment {
    Point p;
    Point q;

    public LineSegment(Point p, Point q) {
        this.p = p;
        this.q = q;
    }

    public Line draw() {
        Line line = new Line(p.getX(), p.getY(), q.getX(), q.getY());
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(2);
        return line;
    }
}
