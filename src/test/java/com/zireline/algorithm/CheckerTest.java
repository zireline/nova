package com.zireline.algorithm;

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

        Checker checker = new Checker();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == 1) {
                    List<int[]> alignedCoords = checker.getAllAligned(board, row, col, 1);
                    for (int[] coord : alignedCoords) {
                        assertEquals(1, board[coord[0]][coord[1]]);
                        System.out.println("Aligned: " + coord[0] + ", " + coord[1]);
                    }
                }
            }
        }
    }
}
