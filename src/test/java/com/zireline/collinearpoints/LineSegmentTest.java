package com.zireline.collinearpoints;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LineSegmentTest extends ApplicationTest {

    private LineSegment lineSegment;
    private Pane pane;

    @Override
    public void start(Stage stage) {
        Point p = new Point(50, 50);
        Point q = new Point(100, 100);
        lineSegment = new LineSegment(p, q);
        pane = new Pane();
        Scene scene = new Scene(pane, 200, 200);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void testDraw() {
        javafx.scene.shape.Line line = lineSegment.draw();
        pane.getChildren().add(line);
        assertTrue(pane.getChildren().contains(line), "The line was not drawn on the pane");
    }
}