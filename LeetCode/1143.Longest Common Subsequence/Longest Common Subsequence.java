// Problem: 1143. Longest Common Subsequence (Medium)
// Link: https://leetcode.com/problems/longest-common-subsequence

import java.util.*;

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();
        if (len1 > len2) {
            String tmp = text1;
            text1 = text2;
            text2 = tmp;
        }
        len1 = text1.length();
        len2 = text2.length();
        int[]dp = new int[len1 + 1];
        
        for (int i = 1; i <= len2; i++) {
            int prev = 0;
            for (int j = 1; j <= len1; j++) {
                int tmp = dp[j];
                if (text1.charAt(j - 1) == text2.charAt(i - 1)) {
                    dp[j] = prev + 1;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
                prev = tmp;
            }
        }
        return dp[len1];    
    }
}