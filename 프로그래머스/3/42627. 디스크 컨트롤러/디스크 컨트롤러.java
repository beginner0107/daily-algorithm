import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
        
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> {
            if (a[1] != b[1]) return Integer.compare(a[1], b[1]);
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[2], b[2]);
        });
        
        int completed = 0;
        int currentTime = 0;
        int totalTime = 0;
        int jobIndex = 0;
        
        while(completed < jobs.length) {
            while (jobIndex < jobs.length && currentTime >= jobs[jobIndex][0]) {
                queue.add(new int[]{jobs[jobIndex][0], jobs[jobIndex][1], jobIndex});
                jobIndex++;
            }
            
            if (queue.isEmpty()) {
                currentTime = jobs[jobIndex][0];
            }
            else {
                int[] arr = queue.poll();
                currentTime += arr[1];
                totalTime += (currentTime - arr[0]);
                completed++;
            }
        }
        return totalTime / jobs.length;
    }
}