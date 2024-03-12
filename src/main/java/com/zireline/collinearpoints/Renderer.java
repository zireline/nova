package com.zireline.collinearpoints;

import java.util.List;

import com.zireline.collinearpoints.algorithm.Enums;
import com.zireline.collinearpoints.display.GridPlane;

public class Renderer {
  public static void render(GridPlane root, List<Point> points, List<LineSegment> segments) {

    // USAGE
    for (LineSegment segment : segments) {
      System.out.println("SEGMENT: " + segment.toString());

      double scaledXP = segment.getP().getX() * Enums.DEFAULT_SCALE_X;
      double scaledYP = segment.getP().getY() * Enums.DEFAULT_SCALE_Y;

      double scaledXQ = segment.getQ().getX() * Enums.DEFAULT_SCALE_X;
      double scaledYQ = segment.getQ().getY() * Enums.DEFAULT_SCALE_Y;

      segment.setP(new Point(scaledXP, scaledYP));
      segment.setQ(new Point(scaledXQ, scaledYQ));

      root.drawShape(segment);
    }

    for (Point currentPoint : points) {
      double scaledX = currentPoint.getX() * Enums.DEFAULT_SCALE_X;
      double scaledY = currentPoint.getY() * Enums.DEFAULT_SCALE_Y;

      currentPoint.setX(scaledX);
      currentPoint.setY(scaledY);

      root.drawShape(currentPoint);

      // // Create a label for the point
      // Label label = new Label("(" + currentPoint.getX() + ", " +
      // currentPoint.getY() + ")");
      // label.setLayoutX(currentPoint.getX());
      // label.setLayoutY(currentPoint.getY());
      // label.setTranslateX(20);
      // label.setTranslateY(500 - 20);

      // label.setScaleY(-1);

      // // Add the label to the root
      // root.getChildren().add(label);
    }

  }
}
