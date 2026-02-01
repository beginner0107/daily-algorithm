import java.util.*;
class Solution {
    public class Node implements Comparable<Node> {
        int start;
        int cost;
        
        Node(int start, int cost) {
            this.start = start;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) graph.add(new ArrayList<>());
        for (int i = 0; i < fares.length; i++) {
            graph.get(fares[i][0]).add(new Node(fares[i][1], fares[i][2]));
            graph.get(fares[i][1]).add(new Node(fares[i][0], fares[i][2]));
        }
        int[] sc = dijkstra(graph, n, s);
        int[] ac = dijkstra(graph, n, a);
        int[] bc = dijkstra(graph, n, b);
        
        int answer = Integer.MAX_VALUE;
        for (int i = 1; i < n + 1; i++) {
            if (sc[i] != Integer.MAX_VALUE && ac[i] != Integer.MAX_VALUE && bc[i] != Integer.MAX_VALUE) {
                answer = Math.min(answer, sc[i] + ac[i] + bc[i]);
            }        
        }
        
        return answer;
    }
    
    public int[] dijkstra(List<List<Node>> graph, int n, int start) {
        final int INF = Integer.MAX_VALUE;
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[0] = 0;
        dist[start] = 0;
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if (dist[cur.start] < cur.cost) continue;
            
            for (Node nxt : graph.get(cur.start)) {
                int newDist = nxt.cost + dist[cur.start];
                if (newDist < dist[nxt.start]) {
                    dist[nxt.start] = newDist;
                    pq.add(new Node(nxt.start, newDist));
                }
            }
        }
        
        return dist;
    }
}