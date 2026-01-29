// Problem: 207. Course Schedule (Medium)
// Link: https://leetcode.com/problems/course-schedule

import java.util.*;

/**
1 <= numCourses(수강해야할 강의) <= 2000
0 <= prerequisites.length(선이수 과목 정보가 담긴 배열의 개수) <= 5000
prerequisites[i].length == 2 (내부는 2로 고정)
0 <= ai, bi < numCourses

강의 ai를 수강하기 위해서는, 반드시 강의 bi을 먼저 들어야 한다.
(ai가 들어야할 과목, bi가 선수 과목)
위상정렬사용해서 푸는 문제 같음
 */

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] indegree = new int[numCourses]; // 0부터 시작한다.
        for (int[] pre : prerequisites) {
            graph.putIfAbsent(pre[1], new ArrayList<>()); // 만약 pre[1]이 존재하면 빈 배열을 넣어준다
            graph.get(pre[1]).add(pre[0]);
            indegree[pre[0]]++;
        }
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[numCourses];
        int[] order = new int[numCourses];
        int count = 0;
        for (int c = 0; c < numCourses; c++) {
            if (indegree[c] == 0) {
                queue.add(c);
                visited[c] = true;
                order[count] = c;
                count++;
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : graph.getOrDefault(cur, new ArrayList<>())) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    visited[next] = true;
                    order[count] = next;
                    count++;
                    queue.add(next);
                }
            }
        }
        for (int c = 0; c < numCourses; c++) {
            if (!visited[c]) return false;
        }
        return true;
    }
}