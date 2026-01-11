// Problem: 51. N-Queens (Hard)
// Link: https://leetcode.com/problems/n-queens

import java.util.*;
// 퀸은 대각선, 위 아래 제한 없이 움직일 수 있음
// N queen이 될 수 있는 모든 경우의 수를 구하시오.
// 1 <= n <= 9 완탐으로 풀어도 됨
class Solution {
    List<List<String>> boards = new ArrayList<>();
    boolean[] cols;
    Set<Integer> diag1 = new HashSet<>();
    Set<Integer> diag2 = new HashSet<>();
    int n;

    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        cols = new boolean[n];
        backtracking(0, new int[n]);
        return boards;
    }

    private void backtracking(int row, int[] pos) {
        if (row == n) {
            boards.add(buildBoard(pos));
            return;
        }

        for (int col = 0; col < n; col++) {
            if (cols[col]) continue;
            if (diag1.contains(row + col)) continue;
            if (diag2.contains(row - col)) continue;

            cols[col] = true;
            diag1.add(row + col);
            diag2.add(row - col);
            pos[row] = col;

            backtracking(row + 1, pos);

            cols[col] = false;
            diag1.remove(row + col);
            diag2.remove(row - col);
        }
    }

    private List<String> buildBoard(int[] pos) {
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append(pos[i] == j ? "Q" : ".");
            }
            board.add(sb.toString());
        }
        return board;
    }
}
