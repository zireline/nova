package com.zireline.collinearpoints;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class PointTest extends Application {

        @Test
        public void testDraw() {
            launch();
        }

        @Override
        public void start(Stage stage) {
            Point point = new Point(50, 50);
            javafx.scene.shape.Circle circle = (Circle) point.draw();

            Pane pane = new Pane();
            pane.getChildren().add(circle);

            assertTrue(pane.getChildren().contains(circle), "The circle was not drawn on the pane");

            Scene scene = new Scene(pane, 200, 200);
            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }

    // import javafx.scene.Scene;
    // import javafx.scene.layout.Pane;
    // import javafx.stage.Stage;
    // import org.junit.Test;
    // import org.testfx.framework.junit.ApplicationTest;

    // import static org.junit.Assert.assertTrue;

    // public class PointTest extends ApplicationTest {

    //     private Point point;
    //     private Pane pane;

    //     @Override
    //     public void start(Stage stage) {
    //         point = new Point(50, 50);
    //         pane = new Pane();
    //         Scene scene = new Scene(pane, 200, 200);
    //         stage.setScene(scene);
    //         stage.show();
    //     }

    //     @Test
    //     public void testDraw() {
    //         javafx.scene.shape.Circle circle = point.draw();
    //         pane.getChildren().add(circle);
    //         assertTrue(pane.getChildren().contains(circle), "The circle was not drawn on the pane");
    //     }
    // }

