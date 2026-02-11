// Problem: 746. Min Cost Climbing Stairs (Easy)
// Link: https://leetcode.com/problems/min-cost-climbing-stairs

class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int[] ans = new int[cost.length + 1];
        for (int i = 0; i < cost.length; i++) ans[i] = cost[i];
        for (int i = 2; i < ans.length; i++) {
            ans[i] = Math.min(ans[i - 2] + ans[i], ans[i - 1] + ans[i]);
        }
        return ans[ans.length - 1];
    }
}