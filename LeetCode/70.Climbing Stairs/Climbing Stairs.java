// Problem: 70. Climbing Stairs (Easy)
// Link: https://leetcode.com/problems/climbing-stairs

class Solution {
    public int climbStairs(int n) {
        int[] ans = new int[n + 1];
        ans[0] = 1;
        ans[1] = 1;
        for (int i = 2; i <= n; i++) {
            ans[i] = ans[i - 1] + ans[i - 2];
        }
        return ans[n];
    }
}