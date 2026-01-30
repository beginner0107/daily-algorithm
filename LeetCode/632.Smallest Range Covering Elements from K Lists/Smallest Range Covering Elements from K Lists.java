// Problem: 632. Smallest Range Covering Elements from K Lists (Hard)
// Link: https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists

import java.util.*;

class Solution {
    class Node implements Comparable<Node>{
        int value;
        int listIdx;
        int elemIdx;

        Node(int value, int listIdx, int elemIdx) {
            this.value = value;
            this.listIdx = listIdx;
            this.elemIdx = elemIdx;
        }

        @Override
        public int compareTo(Node other) {
            if (this.value != other.value) {
                return Integer.compare(this.value, other.value);
            }
            return Integer.compare(this.listIdx, other.listIdx);
        }
    }

    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int maxVal = Integer.MIN_VALUE;
        int start = 0, end = Integer.MAX_VALUE;
        for (int i = 0; i < nums.size(); i++) {
            int val = nums.get(i).get(0); 
            pq.add(new Node(val, i, 0)); 
            maxVal = Math.max(maxVal, val);
        }

        while(!pq.isEmpty()) {
            Node minNode = pq.poll();
            int currentMin = minNode.value;

            if (maxVal - currentMin < end - start) {
                start = currentMin;
                end = maxVal;
            }

            int nextElemIdx = minNode.elemIdx + 1;
            if (nextElemIdx < nums.get(minNode.listIdx).size()) {
                int nextVal = nums.get(minNode.listIdx).get(nextElemIdx);
                pq.add(new Node(nextVal, minNode.listIdx, nextElemIdx));
                maxVal = Math.max(maxVal, nextVal);
            } else {
                break; 
            }
        }
        return new int[]{start, end};
    }
}