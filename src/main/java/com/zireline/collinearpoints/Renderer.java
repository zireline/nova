package com.zireline.collinearpoints;

import java.util.List;

import com.zireline.collinearpoints.display.GridPlane;

public class Renderer {
  public static void render(GridPlane root, List<Point> points) {

    for (int i = 0; i < points.size() - 1; i++) {
      Point currentPoint = points.get(i);
      Point nextPoint = points.get(i + 1);
      root.drawShape(currentPoint.drawTo(nextPoint));
      root.drawShape(currentPoint);
      System.out.println("Drew point: " + currentPoint + "to " + nextPoint);
    }
  }
}
