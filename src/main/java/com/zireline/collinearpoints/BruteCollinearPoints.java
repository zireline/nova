package com.zireline.collinearpoints;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//import com.zireline.collinearpoints.LineSegment;
//import com.zireline.collinearpoints.Point;

public class BruteCollinearPoints {
    private final List<LineSegment> segments = new ArrayList<>();

    public BruteCollinearPoints(List<Point> points) {
        if (points == null) {
            throw new IllegalArgumentException("Points array cannot be null");
        }   


        // // Checker for duplicated points and null
        // Set<Point> pointSet = new HashSet<>();
        // for (Point point : points) {
        //     if (point == null || !pointSet.add(point)) {
        //         throw new IllegalArgumentException("Points must not be either duplicated or null");
        //     }
        // }

        for (int firstPoint = 0; firstPoint < points.size() - 3; firstPoint++) {
            for (int secondPoint = firstPoint + 1; secondPoint < points.size() - 2; secondPoint++) {
                for (int thirdPoint = secondPoint + 1; thirdPoint < points.size() - 1; thirdPoint++) {
                    for (int fourthPoint = thirdPoint + 1; fourthPoint < points.size(); fourthPoint++) {
                        System.out.println("Checking points: " + firstPoint + ", " + secondPoint + ", " + thirdPoint + ", " + fourthPoint);
                        if (areCollinear(points.get(firstPoint), points.get(secondPoint), points.get(thirdPoint), points.get(fourthPoint))) {
                            segments.add(new LineSegment(points.get(firstPoint), points.get(fourthPoint)));
                            System.out.println("Added segment: " + points.get(firstPoint) + " to " + points.get(fourthPoint));
                        }
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        return segments.size();
    }

    public List<LineSegment> segments() {
        return segments;
    }


    //Checks if they are collinear
    private boolean areCollinear(Point p, Point q, Point r, Point s) {
        double slopePQ = p.slopeTo(q);
        double slopePR = p.slopeTo(r);
        double slopePS = p.slopeTo(s);
        System.out.println("Checking collinearity for points: " + p + ", " + q + ", " + r + ", " + s);
        System.out.println("Slope PQ: " + slopePQ + ", PR: " + slopePR + ", PS: " + slopePS);
        boolean areCollinear = Double.compare(slopePQ, slopePR) == 0 && Double.compare(slopePQ, slopePS) == 0;
        System.out.println("Are points collinear? " + areCollinear);
        return areCollinear;
    }

    @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (LineSegment segment : segments) {
      sb.append(segment).append("\n");
    }
    return sb.toString();
  }
}

