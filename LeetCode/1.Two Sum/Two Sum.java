// Problem: 1. Two Sum (Easy)
// Link: https://leetcode.com/problems/two-sum

import java.util.*;

class Solution {
   private Stack<Integer> st = new Stack<>();
    private int tg;
    private int len;

    public int[] twoSum(int[] nums, int target) {
        st.clear();
        len = nums.length;
        tg = target;

        if (backTracking(nums, 0)) {
            return new int[]{st.get(0), st.get(1)};
        }
        return new int[]{};
    }

    private boolean backTracking(int[] nums, int sIndex) {
        if (st.size() == 2) {
            return (nums[st.get(0)] + nums[st.get(1)]) == tg;
        }

        for (int i = sIndex; i < len; i++) {
            st.add(i);
            if (backTracking(nums, i + 1)) {
                return true;
            }
            st.pop();
        }
        return false;
    }
}