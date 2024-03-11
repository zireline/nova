package com.zireline.collinearpoints.algorithm;

import java.util.*;

import com.zireline.collinearpoints.LineSegment;
import com.zireline.collinearpoints.Point;

public class FastCollinearPoints {
  private List<LineSegment> segments = new ArrayList<LineSegment>();

  public FastCollinearPoints(List<Point> points) {

    if (points == null) {
      throw new IllegalArgumentException("argument is null");
    }

    // Check for duplicate points
    Set<Point> pointSet = new HashSet<>(points);
    if (pointSet.size() != points.size()) {
      throw new IllegalArgumentException("Duplicate points detected");
    }

    Point p = points.get(0);

    List<PointsWithSlope> slopedPoints = new ArrayList<>();

    for (int i = 1; i < points.size(); i++) {
      Point q = points.get(i);
      double slope = p.slopeTo(q);
      slopedPoints.add(new PointsWithSlope(q, slope));
    }

    slopedPoints.sort((p1, p2) -> {
      return Double.compare(p1.getSlope(), p2.getSlope());
    });

    PointsWithSlope pws = slopedPoints.get(0);
    for (int i = 0; i < slopedPoints.size(); i++) {
      PointsWithSlope current = slopedPoints.get(i);
      double pOrig = pws.getSlope();
      double pCurr = current.getSlope();

      System.out.println(pOrig + " == " + pCurr);
      if (pOrig == pCurr) {
        segments.add(new LineSegment(pws.getPoint(), current.getPoint()));
      } else {
        pws = current;
      }
    }

  }

  public int numberOfSegments() {
    return segments.size();
  }

  public List<LineSegment> segments() {
    return segments;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (LineSegment segment : segments) {
      sb.append(segment).append("\n");
    }
    return sb.toString();
  }
}

// SEGMENT: LineSegment [(0.0, 167.0) -> (50.0, 117.0)]
// SEGMENT: LineSegment [(50.0, 117.0) -> (167.0, 0.0)]
// SEGMENT: LineSegment [(167.0, 0.0) -> (334.0, 350.0)]
// SEGMENT: LineSegment [(334.0, 350.0) -> (100.0, 117.0)]
// SEGMENT: LineSegment [(100.0, 117.0) -> (234.0, 250.0)]
// SEGMENT: LineSegment [(234.0, 250.0) -> (117.0, 50.0)]
// SEGMENT: LineSegment [(117.0, 50.0) -> (50.0, 67.0)]