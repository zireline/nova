package com.zireline.collinearpoints.algorithm;

import com.zireline.collinearpoints.Point;

public class PointsWithSlope {
  private final Point point;
  private final Double slope;

  public PointsWithSlope(Point point, Double slope) {
    this.point = point;
    this.slope = slope;

    System.out.println("NEW SLOPE: " + slope);
  }

  public Point getPoint() {
    return point;
  }

  public Double getSlope() {
    return slope;
  }

  @Override
  public String toString() {
    return "PointsWithSlope [point=" + point + ", slope=" + slope + "]";
  }
}
