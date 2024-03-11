package com.zireline.collinearpoints;

import java.util.List;

import com.zireline.collinearpoints.algorithm.FastCollinearPoints;
import com.zireline.collinearpoints.display.GridPlane;

import javafx.scene.control.Label;

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
