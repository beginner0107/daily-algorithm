// Problem: 77. Combinations (Medium)
// Link: https://leetcode.com/problems/combinations

import java.util.ArrayList;
import java.util.List;

class Solution {
    List<List<Integer>> al;

    public List<List<Integer>> combine(int n, int k) {
        al = new ArrayList<>();
        recursive(1, new ArrayList<>(), n, k);
        return al;
    }

    private void recursive(int sIndex, List<Integer> li, int n, int k) {
        if (li.size() == k) {
            List<Integer> l = List.copyOf(li);
            al.add(l);
            return;
        }
        for (int i = sIndex; i <= n; i ++) {
            li.add(i);
            recursive(i + 1, li, n, k);
            li.remove(li.size() -1);
        }
    }
}