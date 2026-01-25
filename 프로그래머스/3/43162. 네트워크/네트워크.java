class Solution {
    int answer;
    int n;
    int[][] computers;
    boolean[] visited;
    public int solution(int n, int[][] computers) {
        answer = 0;
        this.n = n;
        this.computers = computers;
        this.visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i);
                answer++;
            }
        }
        
        return answer;
    }
    
    public void dfs(int index) {
        visited[index] = true;
        for (int i = 0; i < n; i++) {
            if (!visited[i] && computers[index][i] == 1) {
                dfs(i);
            }
        }
    }
}