package com.zireline.collinearpoints;

import java.util.List;

import com.zireline.collinearpoints.display.GridPlane;

public class Renderer {
  public static void render(GridPlane root, List<Point> points) {
    double scaleX = 1.0 / 80.0; // Adjust these values as needed
    double scaleY = 1.0 / 80.0; // Adjust these values as needed

    for (int i = 0; i < points.size() - 1; i++) {
      Point currentPoint = new Point((int) (points.get(i).getX() * scaleX), (int) (points.get(i).getY() * scaleY));
      Point nextPoint = new Point((int) (points.get(i + 1).getX() * scaleX), (int) (points.get(i + 1).getY() * scaleY));
      root.drawShape(currentPoint.drawTo(nextPoint));
      root.drawShape(currentPoint);
      System.out.println("Drew point: " + currentPoint + "to " + nextPoint);
    }
  }
}
