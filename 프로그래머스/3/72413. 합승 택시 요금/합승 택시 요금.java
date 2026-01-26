import java.util.*;
/**
1. 그래프 만들기 (fares로부터)
2. 다익스트라 3번 (S, A, B에서 각각)
3. 모든 K에 대해 distS[K] + distA[K] + distB[K] 최솟값 찾기
4. return 최솟값
*/
class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());
        for (int i = 0; i < fares.length; i++) {
            graph.get(fares[i][0]).add(new int[]{fares[i][1], fares[i][2]});
            graph.get(fares[i][1]).add(new int[]{fares[i][0], fares[i][2]});
        }
        int[] sk = dijkstra(s, graph, n);
        int[] ka = dijkstra(a, graph, n);
        int[] kb = dijkstra(b, graph, n);
        for (int i = 1; i <= n; i++) {
            answer = Math.min(answer, sk[i] + ka[i] + kb[i]);
        }
        return answer;
    }
    
    public int[] dijkstra(int start, List<List<int[]>> graph, int n) {
        // start에서 출발해서 모든 노드까지의 최단거리 배열 반환
        int[] answer = new int[n + 1];
        Arrays.fill(answer, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.add(new int[]{0, start});
        answer[start] = 0;
        while(!pq.isEmpty()) {
            int[] cur = pq.poll(); // 0번 인덱스는 거리, 1번 인덱스는 출발지점
            // 갈 수 있는 경로를 탐색해야함
            // path의 각 원소는 {다음노드, 가중치}
            // 현재까지 거리 cur[0]에 가중치를 더하면?
            // 그리고 그 값이 기존에 알고 있던 거리보다 짧으면?
            List<int[]> path = graph.get(cur[1]);
            for (int[] p : path) {
                if (cur[0] + p[1] < answer[p[0]]) {
                    answer[p[0]] = cur[0] + p[1];  
                    pq.add(new int[]{answer[p[0]], p[0]});  
                }
            }
        }
        return answer;
    }
}