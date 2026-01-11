// Problem: 79. Word Search (Medium)
// Link: https://leetcode.com/problems/word-search

import java.util.*;
class Solution {
    char[][]board;
    char[] word;
    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.word = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != this.word[0]) continue;
                if (dfs(0, i, j)) return true;
            }
        }
        return false;
    }

    public boolean dfs(int index, int x, int y) {
        if (index == word.length) return true;
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] != word[index]) return false;
        char temp = board[x][y];
        board[x][y] = '.';
        boolean isOk = dfs(index + 1, x + 1, y) || dfs(index + 1, x - 1, y) || dfs(index + 1, x, y + 1) || dfs(index + 1, x, y - 1);
        board[x][y] = temp;
        return isOk;
    }
}