package com.zireline.collinearpoints;

import javafx.scene.shape.Shape;

public interface Drawable {
  public Shape draw();

  public Shape drawTo(Point that);

}