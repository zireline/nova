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

    // default constructor
    List<PointsWithSlope> slopedPoints = new ArrayList<>();
    final Point origin = points.get(0);

    for (Point point : points) {
      Double slope = origin.slopeTo(point);
      slopedPoints.add(new PointsWithSlope(point, slope));
    }

    // sort sloped points here
    slopedPoints.sort((p1, p2) -> {
      return p1.getSlope().compareTo(p2.getSlope());
    });

    for (PointsWithSlope slopedPoint : slopedPoints) {
      final Point currentPoint = slopedPoint.getPoint();
      if (slopedPoint.getSlope() == origin.slopeTo(currentPoint)) {
        // add to segment
        segments.add(new LineSegment(origin, currentPoint));
        System.out.println("ADDED SEGMENT: " + origin + " -> " + currentPoint);
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
