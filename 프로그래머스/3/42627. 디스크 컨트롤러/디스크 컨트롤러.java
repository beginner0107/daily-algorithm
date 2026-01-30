import java.util.*;

class Solution {
    public class Node implements Comparable<Node>{
        int ix; // 작업 번호
        int st; // 요청 시점
        int tt; // 소요 시간
        
        Node(int ix, int st, int tt) {
            this.ix = ix;
            this.st = st;
            this.tt = tt;
        }
        // 소요시간이 짧은 것
        // 작업의 요청 시각이 빠른 것
        // 작업의 번호가 작은 것
        @Override
        public int compareTo(Node other) {
            if (this.tt != other.tt) return Integer.compare(this.tt, other.tt);
            if (this.st != other.st) return Integer.compare(this.st, other.st);
            return Integer.compare(this.ix, other.ix);
        }
    }
    
    public int solution(int[][] jobs) {
        final int INF = Integer.MAX_VALUE;
        int[] time = new int[jobs.length];
        Arrays.fill(time, INF);
        Queue<Node> pq = new PriorityQueue<>();
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);

        int currentTime = 0;
        int completed = 0;
        int jobIdx = 0;
        int totalTurnaroundTime = 0;

        while (completed < jobs.length) {
            // 현재 시각까지 요청된 모든 작업을 PQ(대기 큐)에 추가
            while (jobIdx < jobs.length && jobs[jobIdx][0] <= currentTime) {
                // Node 클래스에 작성한 compareTo가 여기서 활용됨
                pq.add(new Node(jobIdx, jobs[jobIdx][0], jobs[jobIdx][1]));
                jobIdx++;
            }

            if (pq.isEmpty()) {
                // 대기 중인 작업이 없으면 다음 작업의 요청 시각으로 시각 업데이트
                currentTime = jobs[jobIdx][0];
            } else {
                // 대기 중인 작업 중 가장 우선순위 높은 것 수행
                Node cur = pq.poll();
                currentTime += cur.tt;
                totalTurnaroundTime += (currentTime - cur.st);
                completed++;
            }
        }
        return totalTurnaroundTime / jobs.length;
    }
}