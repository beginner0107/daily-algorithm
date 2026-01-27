import java.util.*;
class Solution {
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    public int solution(int[][] board) {
        int answer = bfs(board.length, board);
        return answer;
    }
    
    public int bfs(int n, int[][] board) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][][][] visited = new boolean[n][n][n][n];
        visited[0][0][0][1] = true;
        queue.add(new int[]{0, 0, 0, 1, 0});
        
        while(!queue.isEmpty()) {
            int[] arr = queue.poll();
            int cr1 = arr[0];
            int cc1 = arr[1];
            int cr2 = arr[2];
            int cc2 = arr[3];
            int count = arr[4];
            if ((cr1 == n - 1 && cc1 == n - 1) || (cr2 == n - 1 && cc2 == n - 1)) {
                return count;
            }
            
            // 4방향으로 방문해봐야함
            for (int i = 0; i < 4; i++) {
                int ncr1 = cr1 + dr[i]; // (ncr1, ncc1)
                int ncc1 = cc1 + dc[i];
                int ncr2 = cr2 + dr[i]; // (ncr2, ncc2)
                int ncc2 = cc2 + dc[i];
                
                // 경계값을 체크한다.
                if (!(isMove(ncr1, ncc1, board) && isMove(ncr2, ncc2, board))) continue;
                if (isWall(ncr1, ncc1, board) || isWall(ncr2, ncc2, board)) continue;
                if (visited[ncr1][ncc1][ncr2][ncc2]) continue;
                visited[ncr1][ncc1][ncr2][ncc2] = true;
                queue.add(new int[]{ncr1, ncc1, ncr2, ncc2, count + 1});
            }
            
            // 이제는 회전하는 거
            int[] dist = {-1, 1};
            for (int d : dist) {
                // cr1 == cr2
                if (cr1 == cr2) { // 가로로 있는 경우
                    int ncr1 = cr1 + d; // row는 같으니깐 굳이 ncr2 계산 x
                    if (!(isMove(ncr1, cc1, board) && isMove(ncr1, cc2, board))) continue;
                    if (isWall(ncr1, cc1, board) || isWall(ncr1, cc2, board)) continue;
                    // 방문을 했는지도 판단해야 한다.
                    if (!visited[cr1][cc1][ncr1][cc1]) { // 
                        visited[cr1][cc1][ncr1][cc1] = true;
                        queue.add(new int[]{cr1, cc1, ncr1, cc1, count + 1});
                    }
                    if (!visited[cr2][cc2][ncr1][cc2]) { // 
                        visited[cr2][cc2][ncr1][cc2] = true;
                        queue.add(new int[]{cr2, cc2, ncr1, cc2, count + 1});
                    }
                }
                // cc1 == cc2
                if (cc1 == cc2) { // 세로인 경우
                    int ncc1 = cc1 + d; // row는 같으니깐 굳이 ncr2 계산 x
                    if (!(isMove(cr1, ncc1, board) && isMove(cr2, ncc1, board))) continue;
                    if (isWall(cr1, ncc1, board) || isWall(cr2, ncc1, board)) continue;
                    // 방문을 했는지도 판단해야 한다.
                    if (!visited[cr1][cc1][cr1][ncc1]) { // 
                        visited[cr1][cc1][cr1][ncc1] = true;
                        queue.add(new int[]{cr1, cc1, cr1, ncc1, count + 1});
                    }
                    if (!visited[cr2][cc2][cr2][ncc1]) { // 
                        visited[cr2][cc2][cr2][ncc1] = true;
                        queue.add(new int[]{cr2, cc2, cr2, ncc1, count + 1});
                    }
                }
            }
        }
        
        return -1;
    }
    
    // 움직일 수 있나 경계값 체크
    public boolean isMove(int row, int col, int[][] board) {
        return row >= 0 && row < board.length && col >= 0 && col < board[0].length;
    }
    
    // 반드시 isMove 이후에 호출
    // 벽이면 true, 벽이 아니면 false
    public boolean isWall(int row, int col, int[][] board) {
        return board[row][col] == 1;
    }
}