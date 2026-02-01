// Problem: 210. Course Schedule II (Medium)
// Link: https://leetcode.com/problems/course-schedule-ii

import java.util.*;

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        int[] order = new int[numCourses];
        boolean[] visited = new boolean[numCourses];

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) graph.add(new ArrayList<>());
        for (int[] pre : prerequisites) {
            graph.get(pre[1]).add(pre[0]);
            indegree[pre[0]]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        int count = 0;
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                order[count] = i;
                visited[i] = true;
                queue.add(i);
                count++;
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int nxt : graph.get(cur)) {
                indegree[nxt]--;
                if (indegree[nxt] == 0) {
                    order[count] = nxt;
                    visited[nxt] = true;
                    queue.add(nxt);
                    count++;
                }
            }
        }

        int val = -1;
        for (int i = 0; i < indegree.length; i++) {
            if (order[i] == 0 && val == -1) {
                val = 0;
            }
            else if (val == 0 && order[i] == 0) {
                return new int[]{};
            }
        }

        return order;
    }
}