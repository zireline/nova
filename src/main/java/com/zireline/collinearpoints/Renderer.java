package com.zireline.collinearpoints;

import java.util.List;

// import com.zireline.collinearpoints.algorithm.FastCollinearPoints;
import com.zireline.collinearpoints.display.GridPlane;

public class Renderer {
  public static void render(GridPlane root, List<Point> points) {
    // USAGE
    // FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);

    // for (LineSegment segment : fastCollinearPoints.segments()) {
    // System.out.println(segment.toString());
    // root.drawShape(segment);
    // }

    Point previousPoint = null;
    for (Point currentPoint : points) {
      if (previousPoint != null) {
        root.drawShape(previousPoint.drawTo(currentPoint));
        root.drawShape(previousPoint);
        System.out.println("Drew point: " + previousPoint + "to " + currentPoint);
      }
      previousPoint = currentPoint;
    }
  }
}
