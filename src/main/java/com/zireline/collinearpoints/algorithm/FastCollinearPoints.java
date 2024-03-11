package com.zireline.collinearpoints.algorithm;

import com.zireline.collinearpoints.LineSegment;
import com.zireline.collinearpoints.Point;

import java.util.*;

public class FastCollinearPoints {
  private List<LineSegment> segments = new ArrayList<>();
  private final int numberOfSegments = 4;

  public FastCollinearPoints(List<Point> points) {
    System.out.println("Initializing FastCollinearPoints with provided points.");

    validateInput(points);

    Point[] pointsCopy = points.toArray(new Point[0]);
    Arrays.sort(pointsCopy);

    for (Point p : pointsCopy) {
      System.out.println("Processing point: " + p);
      findCollinearPoints(p, pointsCopy);
    }

    System.out.println("FastCollinearPoints initialization completed.");
  }

  private void validateInput(List<Point> points) {
    System.out.println("Validating input points.");

    if (points == null) {
      throw new IllegalArgumentException("argument is null");
    }

    // Check for duplicate points
    Set<Point> pointSet = new HashSet<>(points);
    if (pointSet.size() != points.size()) {
      throw new IllegalArgumentException("Duplicate points detected");
    }

    System.out.println("Input points validation completed.");
  }

  private void findCollinearPoints(Point p, Point[] points) {
    System.out.println("Finding collinear points for point: " + p);

    Point[] pointsOther = points.clone();
    Arrays.sort(pointsOther, p.slopeOrder());

    List<Point> collinearPoints = new ArrayList<>();
    collinearPoints.add(p);

    double slope = p.slopeTo(pointsOther[0]);
    for (int i = 1; i < pointsOther.length; i++) {
      System.out.println("Processing point: " + pointsOther[i]);

      double newSlope = p.slopeTo(pointsOther[i]);
      if (newSlope == slope) {
        collinearPoints.add(pointsOther[i]);
        System.out.println("Added point to collinear points list: " + pointsOther[i]);
      } else {
        if (collinearPoints.size() >= numberOfSegments) {
          addSegment(collinearPoints);
          System.out.println("Added segment.");
        }
        collinearPoints.clear();
        collinearPoints.add(p);
        collinearPoints.add(pointsOther[i]);
        System.out.println("Cleared collinear points list and added point: " + pointsOther[i]);
        slope = newSlope;
      }
    }
    if (collinearPoints.size() >= numberOfSegments) {
      addSegment(collinearPoints);
      System.out.println("Added final segment for point: " + p);
    }
  }

  private void addSegment(List<Point> collinearPoints) {
    Point min = collinearPoints.stream().min(Point::compareTo).get();
    Point max = collinearPoints.stream().max(Point::compareTo).get();
    segments.add(new LineSegment(min, max));
    System.out.println("Added segment from point " + min + " to point " + max);
  }

  public int numberOfSegments() {
    System.out.println("Getting number of segments.");
    return segments.size();
  }

  public List<LineSegment> segments() {
    System.out.println("Getting segments.");
    return segments;
  }
}