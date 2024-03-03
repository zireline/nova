package com.zireline.collinearpoints.algorithm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class CheckerTest {
  @Test
  public void testGetAllAligned() {
    int[][] board = {
        { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }
    };

    Checker checker = new Checker(board, 1);
    List<int[]> alignedCoords = checker.getAllAligned(4, 4);
    for (int[] coord : alignedCoords) {
      assertEquals(1, board[coord[0]][coord[1]]);
      System.out.println("Aligned: " + coord[0] + ", " + coord[1]);
    }
  }
}
