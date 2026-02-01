import java.util.*;

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        final int INF = Integer.MAX_VALUE;
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);

        boolean[] isGate = new boolean[n + 1];
        for (int i = 0; i < gates.length; i++) isGate[gates[i]] = true;
        boolean[] isSummit = new boolean[n + 1];
        for (int i = 0; i < summits.length ; i++) isSummit[summits[i]] = true;

        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) graph.add(new ArrayList<>());
        for (int i = 0; i < paths.length; i++) {
            graph.get(paths[i][0]).add(new Node(paths[i][1], paths[i][2]));
            graph.get(paths[i][1]).add(new Node(paths[i][0], paths[i][2]));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < gates.length; i++) {
            pq.add(new Node(gates[i], 0));
            dist[gates[i]] = 0;
        }

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (dist[cur.to] < cur.weight) continue;
            if (isSummit[cur.to]) continue;
            for (Node nxt : graph.get(cur.to)) {
                if (isGate[nxt.to]) continue;
                int intensity = Math.max(nxt.weight ,dist[cur.to]);
                if (dist[nxt.to] > intensity) {
                    dist[nxt.to] = intensity;
                    pq.add(new Node(nxt.to, intensity));
                }
            }
        }

        int minSummit = -1;
        int minIntensity = Integer.MAX_VALUE;

        Arrays.sort(summits);
        for (int summit : summits) {
            if (dist[summit] < minIntensity) {
                minSummit = summit;
                minIntensity = dist[summit];
            }
        }

        return new int[]{minSummit, minIntensity};
    }

    public class Node implements Comparable<Node>{
        int to;
        int weight;

        Node (int to, int weight) {
            this.to = to;
            this.weight = weight;
        }


        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}