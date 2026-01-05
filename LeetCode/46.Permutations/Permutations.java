// Problem: 46. Permutations (Medium)
// Link: https://leetcode.com/problems/permutations

import java.util.*;

class Solution {
    List<List<Integer>> al;
    boolean[] b;
    public List<List<Integer>> permute(int[] nums) {
        al = new ArrayList<>();
        b = new boolean[nums.length];
        recursive(nums, new ArrayList<>());
        return al;
    }

    private void recursive(int[]nums, List<Integer>li) {
        if (li.size() == nums.length) {
            List<Integer> l = List.copyOf(li);
            al.add(l);
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (b[i]) continue;
            b[i] = true;
            li.add(nums[i]);
            recursive(nums, li);
            b[i] = false;
            li.remove(li.size() - 1);
        }
    }
}