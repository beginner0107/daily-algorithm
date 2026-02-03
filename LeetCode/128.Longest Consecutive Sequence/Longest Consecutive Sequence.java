// Problem: 128. Longest Consecutive Sequence (Medium)
// Link: https://leetcode.com/problems/longest-consecutive-sequence

import java.util.*;

class Solution {
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int maxVal = Integer.MIN_VALUE;
        for (int num : set) {
            if (!set.contains(num - 1)) {
                int count = 1;
                int currentNum = num;
                while (set.contains(currentNum + 1)) {
                    currentNum++;
                    count++;
                }
                maxVal = Math.max(maxVal, count);
            }
        }
        return maxVal == Integer.MIN_VALUE ? 0 : maxVal;
    }
}