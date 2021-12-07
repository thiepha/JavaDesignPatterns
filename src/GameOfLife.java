/*
According to the Wikipedia's article: "The Game of Life, also known simply as Life,
is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0).
Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies, as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population..
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

Write a function to compute the next state (after one update) of the board given its current state.
The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.

Example:

Input:
[
[0,1,0],
[0,0,1],
[1,1,1],
[0,0,0]
]
Output:
[
[0,0,0],
[1,0,1],
[0,1,1],
[0,1,0]
]

Follow up:

Could you solve it in-place? Remember that the board needs to be updated at the same time:
You cannot update some cells first and then use their updated values to update other cells.
In this question, we represent the board using a 2D array. In principle, the board is infinite,
which would cause problems when the active area encroaches the border of the array. How would you address these problems?
*/

public class GameOfLife {
    public static void gameOfLife(int[][] board) {
        int len = board.length;
        if (len <= 0) return;
        int sublen = board[0].length;

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < sublen; j++) {
                int no = 0;
                for (int k = -1; k < 2; k++) {
                    for (int l  = -1 ; l < 2; l++) {
                        if (k == 0 && l == 0) continue;
                        if (i + k < 0 || i + k >= len || j + l < 0 || j + l >= sublen) continue;
                        if (board[i + k][j + l] > 0) no++;
                    }
                }

                int cur = board[i][j];
                if (cur == 1 && (no != 2 && no != 3)) cur = 2;
                else if (cur == 0 && no == 3) cur = -1;
                board[i][j] = cur;
            }
        }
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < sublen; j++) {
                if (board[i][j] == -1) board[i][j] = 1;
                else if (board[i][j] == 2) board[i][j] = 0;
            }
        }
    }

    static void print(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            System.out.print(java.util.Arrays.toString(board[i]) + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] board = {{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
        print(board);
        gameOfLife(board);
        print(board);
    }
}
