/*
문제 파악
1) 입출력
- 입력: 컴퓨터의 개수 n, 연결에 대한 정보가 담긴 2차원 배열
- 출력: 네트워크의 개수 answer
2) 핵심규칙
- i번 컴퓨터와 j번 컴퓨터가 연결되어 있으면 computers[i][j]를 1로 표현한다.
- computers[i][j]는 항상 1입니다.
3) 제약 사항
- 컴퓨터의 개수 n은 1이상 200이하의 자연수입니다.
- 각 컴퓨터는 0 ~ n - 1인 정수로 표현 됩니다.

접근 방법
1) 알고리즘
- BFS, DFS
2) 자료구조
- boolean[] visited 배열
3) 의사 코드
- 컴퓨터의 개수가 n개 있다. 컴퓨터 2차원 배열을 n의 사이즈를 가진다.
- 네트워크 하나를 최대 깊이 까지 내려간 후 방문 처리를 해주면 됩니다.
*/
class Solution {
    int n; 
    int[][] computers;
    boolean[] visited;
    int answer;
    public int solution(int n, int[][] computers) {
        answer = 0;
        this.computers = computers;
        this.visited = new boolean[n];
        this.n = n;
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