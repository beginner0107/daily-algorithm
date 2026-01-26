import java.util.*;
class Solution {
    int n;
    int[][] board;
    boolean[][][][] visited;
    
    public int solution(int[][] board) {
        this.n = board.length;
        this.board = board;
        this.visited = new boolean[n][n][n][n];
        
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0, 0, 1, 0});
        visited[0][0][0][1] = true;
        
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int r1 = arr[0], c1 = arr[1];
            int r2 = arr[2], c2 = arr[3];
            int cost = arr[4];
            
            if ((r1 == n-1 && c1 == n-1) || (r2 == n-1 && c2 == n-1)) {
                return cost;
            }
            
            move(queue, r1, c1, r2, c2, cost);
            rotate(queue, r1, c1, r2, c2, cost);
        }
        return -1;
    }
    
    void move(Queue<int[]> queue, int r1, int c1, int r2, int c2, int cost) {
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        for (int i = 0; i < 4; i++) {
            int nr1 = r1 + dr[i], nc1 = c1 + dc[i];
            int nr2 = r2 + dr[i], nc2 = c2 + dc[i];
            tryAdd(queue, nr1, nc1, nr2, nc2, cost + 1);
        }
    }
    
    void rotate(Queue<int[]> queue, int r1, int c1, int r2, int c2, int cost) {
        boolean isHorizontal = (r1 == r2);
        
        for (int d : new int[]{-1, 1}) {
            if (isHorizontal) {
                // 대각선 체크
                if (!canMove(r1 + d, c1) || !canMove(r2 + d, c2)) continue;
                // 왼쪽 축, 오른쪽 축 회전
                tryAdd(queue, r1, c1, r1 + d, c1, cost + 1);
                tryAdd(queue, r2, c2, r2 + d, c2, cost + 1);
            } else {
                // 대각선 체크
                if (!canMove(r1, c1 + d) || !canMove(r2, c2 + d)) continue;
                // 위쪽 축, 아래쪽 축 회전
                tryAdd(queue, r1, c1, r1, c1 + d, cost + 1);
                tryAdd(queue, r2, c2, r2, c2 + d, cost + 1);
            }
        }
    }
    
    void tryAdd(Queue<int[]> queue, int r1, int c1, int r2, int c2, int cost) {
        if (canMove(r1, c1) && canMove(r2, c2) && !visited[r1][c1][r2][c2]) {
            visited[r1][c1][r2][c2] = true;
            queue.add(new int[]{r1, c1, r2, c2, cost});
        }
    }
    
    boolean canMove(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n && board[r][c] == 0;
    }
}