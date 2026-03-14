// Problem: 79. Word Search (Medium)
// Link: https://leetcode.com/problems/word-search

import java.util.*;
class Solution {
    char[][] board;
    char[] words;
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};
    public boolean exist(char[][] board, String word) {
        this.board = board;
        words = word.toCharArray();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (words[0] == board[row][col]) {
                    if (dfs(row, col, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean dfs(int row, int col, int wordIdx) {
        if (wordIdx == words.length - 1) return true;
        char temp = board[row][col];
        board[row][col] = '.';
        for (int i = 0; i < 4; i++) {
            int tr = row + dx[i];
            int tc = col + dy[i];
            int wIdx = wordIdx + 1;
            if (tr < 0 || tr >= board.length || tc < 0 || tc >= board[row].length) continue;
            if (wIdx < words.length) {
                if (board[tr][tc] == words[wIdx]) {
                    if (dfs(tr, tc, wIdx)) {
                        return true;
                    }
                }
            }
        }
        board[row][col] = temp;
        return false;
    }
}