// Problem: 53. Maximum Subarray (Medium)
// Link: https://leetcode.com/problems/maximum-subarray

class Solution {
    public int maxSubArray(int[] nums) {
        if (nums.length == 1) return nums[0];
        int maxValue = nums[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i] = Math.max(nums[i], nums[i - 1] + nums[i]);
            maxValue = Math.max(maxValue, nums[i]);
        }
        return maxValue;
    }
}