// Problem: 3507. Minimum Pair Removal to Sort Array I (Easy)
// Link: https://leetcode.com/problems/minimum-pair-removal-to-sort-array-i

import java.util.*;

class Solution {
    public int minimumPairRemoval(int[] nums) {
        List<Integer> arr = new ArrayList<>();
        for (int x : nums) arr.add(x);

        int ops = 0;

        while (!isNonDecreasing(arr)) {
            int i = findLeftmostMinSumPairIndex(arr);
            mergePairAt(arr, i);
            ops++;
        }

        return ops;
    }

    private boolean isNonDecreasing(List<Integer> arr) {
        for (int k = 0; k < arr.size() - 1; k++) {
            if (arr.get(k) > arr.get(k + 1)) return false; 
        }
        return true;
    }

    private int findLeftmostMinSumPairIndex(List<Integer> arr) {
        int minSum = Integer.MAX_VALUE;
        int minIdx = 0;
        for (int i = 0; i < arr.size() - 1; i++) {
            int sum = arr.get(i) + arr.get(i + 1);
            if (sum < minSum) {
                minSum = sum;
                minIdx = i;
            }
        }
        return minIdx;
    }

    private void mergePairAt(List<Integer> arr, int i) {
        int merged = arr.get(i) + arr.get(i + 1);
        arr.set(i, merged);
        arr.remove(i + 1);
    }
}
