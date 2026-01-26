import java.util.*;
class Solution {
    public int solution(int[][] board) {
        int n = board.length;
        Queue<int[]> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        
        // 시작: (0,0)-(0,1) 가로 상태
        queue.add(new int[]{0, 0, 0, 1, 0});
        visited.add("0,0,0,1");
        
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int r1 = arr[0], c1 = arr[1];
            int r2 = arr[2], c2 = arr[3];
            int cost = arr[4];
            
            // 종료 조건
            if ((r1 == n-1 && c1 == n-1) || (r2 == n-1 && c2 == n-1)) {
                return cost;
            }
            
            // 1. 상하좌우 이동 (둘 다 같이)
            int[] dr = {-1, 1, 0, 0};
            int[] dc = {0, 0, -1, 1};
            for (int i = 0; i < 4; i++) {
                int nr1 = r1 + dr[i], nc1 = c1 + dc[i];
                int nr2 = r2 + dr[i], nc2 = c2 + dc[i];
                
                if (isValid(nr1, nc1, n) && isValid(nr2, nc2, n) 
                    && board[nr1][nc1] == 0 && board[nr2][nc2] == 0) {
                    String state = nr1 + "," + nc1 + "," + nr2 + "," + nc2;
                    if (!visited.contains(state)) {
                        visited.add(state);
                        queue.add(new int[]{nr1, nc1, nr2, nc2, cost + 1});
                    }
                }
            }
            
            // 2. 회전
            if (r1 == r2) {  // 가로 상태
                for (int d : new int[]{-1, 1}) {  // 위로, 아래로
                    if (isValid(r1 + d, c1, n) && isValid(r2 + d, c2, n)
                        && board[r1 + d][c1] == 0 && board[r2 + d][c2] == 0) {
                        // 왼쪽 축 회전
                        String s1 = r1 + "," + c1 + "," + (r1 + d) + "," + c1;
                        if (!visited.contains(s1)) {
                            visited.add(s1);
                            queue.add(new int[]{r1, c1, r1 + d, c1, cost + 1});
                        }
                        // 오른쪽 축 회전
                        String s2 = r2 + "," + c2 + "," + (r2 + d) + "," + c2;
                        if (!visited.contains(s2)) {
                            visited.add(s2);
                            queue.add(new int[]{r2, c2, r2 + d, c2, cost + 1});
                        }
                    }
                }
            } else {  // 세로 상태
                for (int d : new int[]{-1, 1}) {  // 왼쪽으로, 오른쪽으로
                    if (isValid(r1, c1 + d, n) && isValid(r2, c2 + d, n)
                        && board[r1][c1 + d] == 0 && board[r2][c2 + d] == 0) {
                        // 위쪽 축 회전
                        String s1 = r1 + "," + c1 + "," + r1 + "," + (c1 + d);
                        if (!visited.contains(s1)) {
                            visited.add(s1);
                            queue.add(new int[]{r1, c1, r1, c1 + d, cost + 1});
                        }
                        // 아래쪽 축 회전
                        String s2 = r2 + "," + c2 + "," + r2 + "," + (c2 + d);
                        if (!visited.contains(s2)) {
                            visited.add(s2);
                            queue.add(new int[]{r2, c2, r2, c2 + d, cost + 1});
                        }
                    }
                }
            }
        }
        return -1;
    }
    
    boolean isValid(int r, int c, int n) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }
}