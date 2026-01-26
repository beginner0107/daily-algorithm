import java.util.*;

class Solution {
        int[] answer;
        public int[] solution(String[][] places) {
            answer = new int[places.length];
            Arrays.fill(answer, 1);
            int n = 0;
            for (String[] place : places) {
                char[][] seats = new char[5][5]; // 대기실, 줄
                int start = 0;
                for (String pl : place) {
                    seats[start] = pl.toCharArray();
                    start++;
                }
                // 방법1. 반복문을 돌면서 P의 위치에서 BFS를 돌려서 다가갈 수 있는 좌표들의 row, col을 수집하고 멘헤튼 거리를 계산한다.
                boolean existP = false;
                boolean satisfied = true;
                for (int i = 0; i < 5; i++) { // 이건 대기실이 아님
                    for (int j = 0; j < 5; j++) { 
                        if (seats[i][j] == 'P') {
                            existP = true;
                            satisfied = bfs(i, j, seats, n);// bfs를 돌면서 다가갈 수 있는 좌표들을 구한다.
                            if (!satisfied) {
                                break;
                            }
                        } 
                    }
                    // P를 찾았고 -> 멘해튼 거리를 만족하지 못한다면
                    if (existP && !satisfied) {
                        answer[n] = 0;
                        break;
                    } 
                    // if (!existP) answer[n] = 1;
                }
                n++;
            }
            return answer;
        }

    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public boolean bfs(int row, int col, char[][] seats, int n) {
        boolean[][] visited = new boolean[5][5];
        visited[row][col] = true;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{row, col, 0}); // 잠깐만 멘헤튼 거리 == BFS의 거리 누적이 3일 때  P가 존재하면 배열로 0을 보내주면 될듯
        while(!queue.isEmpty()) {
            int[] arr = queue.poll();
            int r = arr[0];
            int c = arr[1];
            int dist = arr[2];

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                // 경계값 체크
                if (nr >= 0 && nr < 5 && nc >= 0 && nc < 5) {
                    // 벽인지 체크
                    if (seats[nr][nc] == 'X') continue;
                    // 빈 테이블인지 응시자의 자리인지
                    // 빈 테이블이라면 확산할 수 있음
                    // 방문하지 않은 곳
                    if (!visited[nr][nc]) {
                        if (seats[nr][nc] == 'O') { // 빈 테이블이라서
                            visited[nr][nc] = true;
                            queue.add(new int[]{nr, nc, dist + 1});
                        }
                        else if (seats[nr][nc] == 'P') { // 멘헤튼 거리로 판단해야함.
                            // 원래 좌표 (r, c) 와 현재 좌표 (nr, nc)를 계산 > 2 -> false
                            // 파티션이 있는 경우.. 좌표로만 계산하면 틀림 dist를 써야 함.
                            // dist가 2이하일 때만 멘해튼 거리 계산할 것?
                            if (dist == 0 || dist == 1) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}