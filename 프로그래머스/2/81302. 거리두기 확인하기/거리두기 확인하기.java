import java.util.*;

class Solution {
    public int[] solution(String[][] places) {
        int n = places.length;
        int[] answer = new int[n];
        // places[대기실][문자열]
        for (int i = 0; i < places.length; i++) {
            String[] place = places[i]; // 5의 크기를 가지는 "OOOOO" 배열
            // 멘헤튼 거리를 지키고 있는 지 확인
            char[][] waitingRoom = createRoom(place);
            // p의 좌표가 필요하다
            boolean isSatisfied = true;
            boolean existsP = false;
            loop:
            for (int k = 0; k < 5; k++) {
                for (int j = 0; j < 5; j++) {
                    if (waitingRoom[k][j] == 'P') {
                        existsP = true;
                        isSatisfied = bfs(k, j, waitingRoom);
                        if (!isSatisfied) break loop;
                    }
                }
            }
            if (!existsP) isSatisfied = true;
            answer[i] = isSatisfied ? 1 : 0;
        }
        return answer;
    }
    
    public boolean bfs(int row, int col, char[][] waitingRoom) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{row, col, 0}); // 초기 P의 좌표
        boolean[][] visited = new boolean[5][5];
        visited[row][col] = true;
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};
        while(!queue.isEmpty()) {
            int[] arr = queue.poll();
            int or = arr[0];
            int oc = arr[1];
            int dist = arr[2];
            
            for (int i = 0; i < 4; i++) {
                int nr = or + dr[i];
                int nc = oc + dc[i];
                
                if (nr >= 0 && nr < 5 && nc >= 0 && nc < 5) {
                    if (!visited[nr][nc] && waitingRoom[nr][nc] != 'X') {
                        // 거리가 2 미만일 때 문제가 된다.
                        if (waitingRoom[nr][nc] == 'P' && dist < 2) {
                            return false;
                        } else if (dist < 2 && waitingRoom[nr][nc] == 'O'){
                            visited[nr][nc] = true;
                            queue.add(new int[]{nr, nc, dist + 1});
                        }
                    }
                }
            }
        }
        return true;
    }
    
    public char[][] createRoom(String[] place) {
        char[][] room = new char[5][5];
        for (int i = 0; i < 5; i++) {
            room[i] = place[i].toCharArray();
        }
        return room;
    }
}