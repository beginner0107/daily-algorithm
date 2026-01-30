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
        @Override
        public int compareTo(Node other) {
            if (this.tt != other.tt) return Integer.compare(this.tt, other.tt);
            if (this.st != other.st) return Integer.compare(this.st, other.st);
            return Integer.compare(this.ix, other.ix);
        }
    }
    
    public int solution(int[][] jobs) {
        Queue<Node> pq = new PriorityQueue<>();
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);

        int currentTime = 0;
        int completed = 0;
        int jobIdx = 0;
        int totalTurnaroundTime = 0;

        while (completed < jobs.length) {
            while (jobIdx < jobs.length && jobs[jobIdx][0] <= currentTime) {
                pq.add(new Node(jobIdx, jobs[jobIdx][0], jobs[jobIdx][1]));
                jobIdx++;
            }

            if (pq.isEmpty()) {
                currentTime = jobs[jobIdx][0];
            } else {
                Node cur = pq.poll();
                currentTime += cur.tt;
                totalTurnaroundTime += (currentTime - cur.st);
                completed++;
            }
        }
        return totalTurnaroundTime / jobs.length;
    }
}