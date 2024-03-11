package com.zireline.collinearpoints;

import java.util.List;

import com.zireline.collinearpoints.algorithm.FastCollinearPoints;
import com.zireline.collinearpoints.display.GridPlane;

public class Renderer {
  public static void render(GridPlane root, List<Point> points) {
    FastCollinearPoints collinearPoints = new FastCollinearPoints(points);

    // USAGE
    for (LineSegment segment : collinearPoints.segments()) {
      System.out.println("SEGMENT: " + segment.toString());
      root.drawShape(segment);
    }

    for (Point currentPoint : points) {
      root.drawShape(currentPoint);
    }

  }
}
