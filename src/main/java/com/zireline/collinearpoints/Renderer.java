package com.zireline.collinearpoints;

import java.util.List;

import com.zireline.collinearpoints.algorithm.Enums;
import com.zireline.collinearpoints.algorithm.FastCollinearPoints;
import com.zireline.collinearpoints.display.GridPlane;

import javafx.scene.control.Label;

public class Renderer {
  public static void render(GridPlane root, List<Point> points) {
    FastCollinearPoints collinearPoints = new FastCollinearPoints(points);

    // USAGE
    for (LineSegment segment : collinearPoints.segments()) {
      System.out.println("SEGMENT: " + segment.toString());

      double scaledXP = Math.ceil(segment.getP().getX() * Enums.DEFAULT_SCALE_X);
      double scaledYP = Math.ceil(segment.getP().getY() * Enums.DEFAULT_SCALE_Y);

      double scaledXQ = Math.ceil(segment.getQ().getX() * Enums.DEFAULT_SCALE_X);
      double scaledYQ = Math.ceil(segment.getQ().getY() * Enums.DEFAULT_SCALE_Y);

      segment.setP(new Point(scaledXP, scaledYP));
      segment.setQ(new Point(scaledXQ, scaledYQ));

      root.drawShape(segment);
    }

    for (Point currentPoint : points) {
      double scaledX = Math.ceil(currentPoint.getX() * Enums.DEFAULT_SCALE_X);
      double scaledY = Math.ceil(currentPoint.getY() * Enums.DEFAULT_SCALE_Y);

      currentPoint.setX(scaledX);
      currentPoint.setY(scaledY);

      root.drawShape(currentPoint);

      // Create a label for the point
      Label label = new Label("(" + currentPoint.getX() + ", " + currentPoint.getY() + ")");
      label.setLayoutX(currentPoint.getX());
      label.setLayoutY(currentPoint.getY());
      label.setTranslateX(20);
      label.setTranslateY(500 - 20);

      label.setScaleY(-1);

      // Add the label to the root
      root.getChildren().add(label);
    }

  }
}
