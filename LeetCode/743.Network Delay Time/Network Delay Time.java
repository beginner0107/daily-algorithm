// Problem: 743. Network Delay Time (Medium)
// Link: https://leetcode.com/problems/network-delay-time

import java.util.*;
class Solution {
    public static class Edge implements Comparable<Edge> {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

        for (int[] time : times) {
            int from = time[0];
            int to = time[1];
            int weight = time[2];
            graph.get(from).add(new Edge(to, weight));
        }

        final int INF = Integer.MAX_VALUE;
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[0] = 0;
        dist[k] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(k, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            if (dist[current.to] < current.weight) continue;

            for (Edge next : graph.get(current.to)) {
                int newDist = current.weight + next.weight;
                if (newDist < dist[next.to]) {
                    dist[next.to] = newDist;
                    pq.add(new Edge(next.to, newDist));
                }
            }
        }

        int maxDelay = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == INF) return -1;
            maxDelay = Math.max(maxDelay, dist[i]);
        }
        return maxDelay;
    }
}