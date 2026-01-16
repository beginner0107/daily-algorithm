// Problem: 42. Trapping Rain Water (Hard)
// Link: https://leetcode.com/problems/trapping-rain-water

import java.util.*;

class Solution {
    public int trap(int[] height) {
        int lt = 0;
        int rt = height.length - 1;

        int maxLt = height[lt];
        int maxRt = height[rt];

        int sum = 0;
        while (lt < rt) {
            if (maxLt < maxRt) {
                lt++;
                if (maxLt > height[lt]) {
                    sum += maxLt - height[lt];
                } else {
                    maxLt = height[lt];
                }
            } else {
                rt--;
                if (maxRt > height[rt]) {
                    sum += maxRt - height[rt];
                } else {
                    maxRt = height[rt];
                }
            }
        }

        return sum;
    }
}