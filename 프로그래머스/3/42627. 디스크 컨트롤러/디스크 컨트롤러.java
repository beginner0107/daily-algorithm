import java.util.*;

class Solution {
    public class Job implements Comparable<Job>{
        int idx; // 작업 번호
        int rt; // requestTime 요청 시간
        int dt; // duration 소요 시간
        
        Job(int idx, int rt, int dt) {
            this.idx = idx;
            this.rt = rt;
            this.dt = dt;
        }
        
        @Override
        public int compareTo(Job o) {
            if (this.dt != o.dt) return Integer.compare(this.dt, o.dt);
            if (this.rt != o.rt) return Integer.compare(this.rt, o.rt);
            return Integer.compare(this.idx, o.idx);
        }
    }
    
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (a, b) -> Integer.compare(a[0], b[0]));
        Queue<Job> pq = new PriorityQueue<>();
        
        int currentTime = 0;
        int totalTime = 0;
        int index = 0;
        int count = 0;
        while (count < jobs.length) {
            while (index < jobs.length && currentTime >= jobs[index][0]) {
                pq.add(new Job(index, jobs[index][0], jobs[index][1]));
                index++;
            }
            if (pq.isEmpty()) {
                currentTime = jobs[index][0];
            }
            else {
                Job cur = pq.poll();
                currentTime += cur.dt; // 소요 시간을 더해준다.
                totalTime += (currentTime - cur.rt);
                count++;
            }
        }
        return totalTime / jobs.length;
    }
}