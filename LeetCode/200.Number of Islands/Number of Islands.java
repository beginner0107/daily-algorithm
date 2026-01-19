// Problem: 200. Number of Islands (Medium)
// Link: https://leetcode.com/problems/number-of-islands

/**
문제 파악

입출력
- 입력: char 이차원 배열
핵심규칙
- 1은 땅, 0은 물
- 땅을 물로 채우는데, 이어져 있는 땅들의 개수 구하기
- 이미 물인 곳은 갈 수 없는 곳
제약사항
- 그리드의 길이 m x n
- m 은 1이상, n 은 300 이하

접근 방법
알고리즘
- DFS로 풀면 적당
자료구조
- 좌표 저장될 배열 int[] * 2, 개수 저장될 int answer
의사코드
- 1. m x n을 순회하면서 1인 값이 나오면 dfs()함수 호출
- 2. dfs()를 돌면 연결된 땅이 다 채워지고 answer를 1증가
- 2-1. dfs는 '상, 하, 좌, 우' 탐색해서 1인 값이 있으면 0으로 grid값을 바꾸고 다시 dfs를 호출
- 2-2. dfs에서 '상, 하, 좌, 우'를 다 탐색해서 더이상 갈 곳이 없으면 자연스럽게 탈출
- 3. (1)로 돌아가서 '1'인 값이 나오면 다시 dfs 호출 
- 4. 반복문이 끝나면 answer를 반환
시간복잡도
- O(m x n x n?)
*/
class Solution {
    int[] dr = {1, -1, 0, 0};
    int[] dc = {0, 0, 1, -1};
    public int numIslands(char[][] grid) {
        int answer = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (isOne(grid, i, j)) {
                    dfs(i, j, grid);
                    answer++;
                }
            }
        }
        return answer;
    }

    private void dfs(int r, int c, char[][] grid) {
        grid[r][c] = 0;

        for (int i = 0; i < 4; i++) {
            int nextRow = r + dr[i];
            int nextCol = c + dc[i];

            // 경계값 체크
            if (nextRow >= 0 && nextRow < grid.length && nextCol >= 0 && nextCol < grid[0].length) {
                // 1인지 체크
                if (isOne(grid, nextRow, nextCol)) {
                    dfs(nextRow, nextCol, grid);
                }
            }
        }
    }

    private boolean isOne(char[][] grid, int row, int col) {
        return grid[row][col] == '1';
    }
}