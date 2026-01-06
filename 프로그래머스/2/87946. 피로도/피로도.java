import java.util.*;
class Solution {
    int[][] dgs;
    int answer;
    boolean[] visited;
    public int solution(int k, int[][] dungeons) {
        answer = Integer.MIN_VALUE;
        dgs = dungeons;
        visited = new boolean[dgs.length];
        backtracking(0, k, 0);
        return answer;
    }
    
    private void backtracking(int count, int currentP, int num) {
        if (count == dgs.length) {
            answer = Math.max(answer, num);
            return;
        }
        for (int i = 0; i < dgs.length; i++) {
            if (visited[i]) continue;
            int cp = dgs[i][0];
            int sp = dgs[i][1];
            visited[i] = true;
            boolean checkP = false;
            if (currentP >= cp) checkP = true; 
            backtracking(count + 1, checkP ? currentP - sp : currentP, checkP ? num + 1 : num);
            visited[i] = false;
        }
    }
}