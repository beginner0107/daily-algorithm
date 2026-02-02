// Problem: 207. Course Schedule (Medium)
// Link: https://leetcode.com/problems/course-schedule

import java.util.*;

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) graph.add(new ArrayList<>());
        for (int[] pre : prerequisites) {
            graph.get(pre[1]).add(pre[0]);
            indegree[pre[0]]++;
        }

        int count = 0;
        Queue<Integer> q = new ArrayDeque<>();
        for (int c = 0; c < numCourses; c++) {
            if (indegree[c] == 0) {
                q.add(c);
                count++;
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int nxt : graph.get(cur)) {
                indegree[nxt]--;
                if (indegree[nxt] == 0) {
                    q.add(nxt);
                    count++;
                }
            }
        }
        return count == numCourses;
    }
}