package com.zireline.collinearpoints;

import java.util.ArrayList;
import java.util.List;

import com.zireline.collinearpoints.LineSegment;
import com.zireline.collinearpoints.Point;

public class BruteCollinearPoints {
	private final ArrayList<LineSegment> segments = new ArrayList<>();

	public BruteCollinearPoints(List<Point> points) {
		if (points == null) {
			throw new IllegalArgumentException("Points array cannot be null");
		}

		for (int i = 0, firstPoint = 0; firstPoint < points.size() - 3; firstPoint++) {
			for (int secondPoint = firstPoint + 1; secondPoint < points.size() - 2; secondPoint++) {
				double slopeFirstSecond = points.get(firstPoint).slopeTo(points.get(secondPoint));
				for (int thirdPoint = secondPoint + 1; thirdPoint < points.size() - 1; thirdPoint++) {
					double slopeFirstThird = points.get(firstPoint).slopeTo(points.get(thirdPoint));
					if (slopeFirstSecond == slopeFirstThird) {
						for (int fourthPoint = thirdPoint + 1; fourthPoint < points.size(); fourthPoint++) {
							double slopeFirstFourth = points.get(firstPoint).slopeTo(points.get(fourthPoint));
							if (slopeFirstSecond == slopeFirstFourth) {
								segments.add(new LineSegment(points.get(firstPoint), points.get(fourthPoint)));
							}
						}
					}
				}
			}
			i++;
		}
	}

	public int numberOfSegments() {
		return segments.size();
	}

	public ArrayList<LineSegment> segments() {
		return segments;
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