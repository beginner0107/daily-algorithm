// Problem: 221. Maximal Square (Medium)
// Link: https://leetcode.com/problems/maximal-square

class Solution {
    public int maximalSquare(char[][] matrix) {
        int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];
        int max = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i - 1][j - 1], dp[i][j - 1])) + 1;
                    max = Math.max(max, dp[i][j]);
                } 
            }
        }
        return max * max;
    }
}