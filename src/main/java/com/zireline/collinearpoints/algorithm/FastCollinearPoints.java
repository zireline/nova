package com.zireline.collinearpoints.algorithm;

import com.zireline.collinearpoints.LineSegment;
import com.zireline.collinearpoints.Point;

import java.util.*;

public class FastCollinearPoints {
  private List<LineSegment> segments = new ArrayList<>();
  private final int numberOfSegments = 4;

  public FastCollinearPoints(List<Point> points) {
    validateInput(points);

    Point[] pointsCopy = points.toArray(new Point[0]);
    Sorter.mergeSort(pointsCopy);

    List<Point> collinearPoints = new ArrayList<>();

    for (Point p : pointsCopy) {
      findCollinearPoints(p, pointsCopy, collinearPoints);
      collinearPoints.clear();
    }
  }

  private void validateInput(List<Point> points) {
    if (points == null) {
      throw new IllegalArgumentException("argument is null");
    }

    // Check for duplicate points
    for (int i = 0; i < points.size(); i++) {
      for (int j = i + 1; j < points.size(); j++) {
        if (points.get(i).compareTo(points.get(j)) == 0) {
          throw new IllegalArgumentException("Duplicate points detected");
        }
      }
    }
  }

  private void findCollinearPoints(Point p, Point[] points, List<Point> collinearPoints) {
    Sorter.mergeSort(points, p.slopeOrder());

    collinearPoints.add(p);

    double slope = p.slopeTo(points[0]);
    for (int i = 1; i < points.length; i++) {
      double newSlope = p.slopeTo(points[i]);

      if (newSlope == slope) {
        collinearPoints.add(points[i]);
      } else {
        if (collinearPoints.size() >= numberOfSegments) {
          addSegment(collinearPoints);
        }
        collinearPoints.clear();
        collinearPoints.add(p);
        collinearPoints.add(points[i]);
        slope = newSlope;
      }
    }

    if (collinearPoints.size() >= numberOfSegments) {
      addSegment(collinearPoints);
    }
  }

  private void addSegment(List<Point> collinearPoints) {
    Point min = collinearPoints.get(0);
    Point max = collinearPoints.get(0);

    for (Point point : collinearPoints) {
      if (point.compareTo(min) < 0)
        min = point;
      if (point.compareTo(max) > 0)
        max = point;
    }

    segments.add(new LineSegment(min, max));
  }

  public int numberOfSegments() {
    return segments.size();
  }

  public List<LineSegment> segments() {
    return segments;
  }
}