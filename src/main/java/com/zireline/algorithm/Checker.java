package com.zireline.algorithm;

import java.util.ArrayList;
import java.util.List;

// public class Checker {
//     public List<int[]> getAllAligned(int[][] board, int startRow, int startColumn, int cellValue) {
//         List<int[]> alignedCoords = new ArrayList<>();
//         alignedCoords.addAll(checkRow(board, startRow, startColumn, cellValue));
//         alignedCoords.addAll(checkColumn(board, startRow, startColumn, cellValue));
//         alignedCoords.addAll(checkDiagonal(board, startRow, startColumn, cellValue));
//         return alignedCoords;
//     }

//     private List<int[]> check(int[][] board, int startRow, int startColumn, int cellValue, boolean isColumnCheck) {
//         List<int[]> coords = new ArrayList<>();
//         for (int i = 0; i < 10; i++) {
//             if ((isColumnCheck ? board[startRow + i][startColumn] : board[startRow][startColumn + i]) == cellValue) {
//                 coords.add(new int[] { startRow + i, startColumn });
//             }
//         }
//         return coords;
//     }

//     public List<int[]> checkRow(int[][] board, int startRow, int startColumn, int cellValue) {
//         return check(board, startRow, startColumn, cellValue, false);
//     }

//     public List<int[]> checkColumn(int[][] board, int startRow, int startColumn, int cellValue) {
//         return check(board, startRow, startColumn, cellValue, true);
//     }

//     public List<int[]> checkDiagonal(int[][] board, int startRow, int startColumn, int cellValue) {
//         List<int[]> coords = new ArrayList<>();
//         for (int i = 0; i < 10; i++) {
//             if (board[startRow + i][startColumn + i] == cellValue) {
//                 coords.add(new int[] { startRow + i, startColumn + i });
//             }
//         }
//         return coords;
//     }
// }


public class Checker {
    public List<int[]> getAllAligned(int[][] board, int startRow, int startColumn, int cellValue) {
        List<int[]> alignedCoords = new ArrayList<>();
        alignedCoords.addAll(checkRow(board, startRow, startColumn, cellValue));
        alignedCoords.addAll(checkColumn(board, startRow, startColumn, cellValue));
        alignedCoords.addAll(checkDiagonal(board, startRow, startColumn, cellValue));
        return alignedCoords;
    }

    private List<int[]> check(int[][] board, int startRow, int startColumn, int cellValue, boolean isColumnCheck) {
        List<int[]> coords = new ArrayList<>();
        for (int i = 0; i < board.length && startRow + i < board.length && startColumn + i < board[0].length; i++) {
            if ((isColumnCheck ? board[startRow + i][startColumn] : board[startRow][startColumn + i]) == cellValue) {
                coords.add(new int[] { startRow + i, startColumn });
            }
        }
        return coords;
    }

    public List<int[]> checkRow(int[][] board, int startRow, int startColumn, int cellValue) {
        return check(board, startRow, startColumn, cellValue, false);
    }

    public List<int[]> checkColumn(int[][] board, int startRow, int startColumn, int cellValue) {
        return check(board, startRow, startColumn, cellValue, true);
    }

    public List<int[]> checkDiagonal(int[][] board, int startRow, int startColumn, int cellValue) {
        List<int[]> coords = new ArrayList<>();
        for (int i = 0; i < board.length && startRow + i < board.length && startColumn + i < board[0].length; i++) {
            if (board[startRow + i][startColumn + i] == cellValue) {
                coords.add(new int[] { startRow + i, startColumn + i });
            }
        }
        return coords;
    }
}