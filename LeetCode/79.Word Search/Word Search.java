// Problem: 79. Word Search (Medium)
// Link: https://leetcode.com/problems/word-search

import java.util.*;
class Solution {
    int[] dx = new int[]{-1, 1, 0, 0};
    int[] dy = new int[]{0, 0, -1, 1};
    char[][] bd;
    char[] wd;
    boolean[][] cb;
    boolean an;
    public boolean exist(char[][] board, String word) {
        bd = board;
        wd = word.toCharArray();
        cb = new boolean[board.length][board[0].length];
        an = false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (wd[0] != bd[i][j]) continue;
                cb[i][j] = true;
                if (dfs(i, j, 1)) {
                    return true;
                }
                cb[i][j] = false;
            }
        }
        return false;
    }

    private boolean dfs(int a, int b, int level) {
        if (level == wd.length) {
            return true;
        }
        for (int i = 0; i < 4; i++) {
            int x = dx[i] + a;
            int y = dy[i] + b;
            if (x >= 0 && x < bd.length && y >= 0 && y < bd[0].length) {
                if (bd[x][y] == wd[level] && !cb[x][y]) {
                    cb[x][y] = true;
                    if (an || dfs(x, y, level + 1)) {
                        an = true;
                        break;
                    }
                    cb[x][y] = false;
                }
            }
        }
        return an ? true : false;
    }
}