import java.util.*;
class Solution {
    int[][] dgs;
    int answer;
    boolean[] visited;
    public int solution(int k, int[][] dungeons) {
        answer = Integer.MIN_VALUE;
        dgs = dungeons;
        visited = new boolean[dgs.length];
        backtracking(k, 0);
        return answer;
    }
    
    private void backtracking(int currentP, int num) {
        if (num > dgs.length) return;
        answer = Math.max(answer, num);
        
        for (int i = 0; i < dgs.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            if (currentP >= dgs[i][0]) {
                backtracking(currentP - dgs[i][1], num + 1);
            }
            visited[i] = false;
        }
    }
}