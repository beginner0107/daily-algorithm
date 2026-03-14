// Problem: 1. Two Sum (Easy)
// Link: https://leetcode.com/problems/two-sum

import java.util.*;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        int idx = 0;
        for (int num : nums) {
            int tmp = target - num;
            if (map.containsKey(tmp)) {
                return new int[]{map.get(tmp), idx};
            }
            map.put(num, idx);
            idx++;
        }
        return null;
    }
}