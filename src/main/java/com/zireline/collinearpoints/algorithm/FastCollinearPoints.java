package com.zireline.collinearpoints.algorithm;

import java.util.ArrayList;
import java.util.List;

import com.zireline.collinearpoints.LineSegment;
import com.zireline.collinearpoints.Point;

public class FastCollinearPoints {
  private final List<LineSegment> segments = new ArrayList<>();

  public FastCollinearPoints(List<Point> points) {
    // default constructor
  }

  public int numberOfSegments() {
    return 0;
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
