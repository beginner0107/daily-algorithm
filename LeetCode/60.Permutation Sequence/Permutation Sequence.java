// Problem: 60. Permutation Sequence (Hard)
// Link: https://leetcode.com/problems/permutation-sequence

import java.util.*;
class Solution {
    boolean[] visited;
    int[] nums;
    String at;
    int sc;
    boolean ib;
    public String getPermutation(int n, int k) {
        nums = new int [n];
        visited = new boolean[n];
        at = "";

        for (int i = 1; i <= n; i++) {
            nums[i - 1] = i;
        }

        backtracking(n, "", k);
        return at;
    }

    private void backtracking(int n, String st, int k) {
        if (st.length() == n) {
            sc++;
            if (sc == k) {
                at = st;
                ib = true;
            }
            return;
        }
        if (ib) return;

        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            st += String.valueOf(nums[i]);
            backtracking(n, st, k);
            visited[i] = false;
            st = st.substring(0, st.length() -1);
        }
    }
}
