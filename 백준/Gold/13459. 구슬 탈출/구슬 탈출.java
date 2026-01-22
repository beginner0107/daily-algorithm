import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static char[][] map;
    // [빨강행][빨강열][파랑행][파랑열] 방문 체크
    static boolean[][][][] visited = new boolean[11][11][11][11];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        int[] startRed = new int[2];
        int[] startBlue = new int[2];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'R') { startRed[0] = i; startRed[1] = j; }
                if (map[i][j] == 'B') { startBlue[0] = i; startBlue[1] = j; }
            }
        }

        // 구슬 탈출 1은 성공 여부만 확인 (DFS로도 가능)
        if (recursive(startRed, startBlue, 1)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    private static boolean recursive(int[] redBall, int[] blueBall, int count) {
        if (count > 10) return false; // 10번 넘으면 실패

        // 현재 상태(빨강 위치, 파랑 위치)를 방문한 적이 있다면 스킵
        if (visited[redBall[0]][redBall[1]][blueBall[0]][blueBall[1]]) return false;
        visited[redBall[0]][redBall[1]][blueBall[0]][blueBall[1]] = true;

        // 상, 하, 좌, 우 4가지 방향 시도
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for (int i = 0; i < 4; i++) {
            int nrr = redBall[0], nrc = redBall[1];
            int nbr = blueBall[0], nbc = blueBall[1];
            int rDist = 0, bDist = 0;
            boolean rIn = false, bIn = false;

            // 빨간 구슬 이동 (사용자님의 '끝까지 이동' 로직)
            while (map[nrr + dr[i]][nrc + dc[i]] != '#') {
                nrr += dr[i];
                nrc += dc[i];
                rDist++;
                if (map[nrr][nrc] == 'O') { rIn = true; break; }
            }

            // 파란 구슬 이동
            while (map[nbr + dr[i]][nbc + dc[i]] != '#') {
                nbr += dr[i];
                nbc += dc[i];
                bDist++;
                if (map[nbr][nbc] == 'O') { bIn = true; break; }
            }

            if (bIn) continue; // 파란 구슬 빠지면 이 방향은 무조건 실패
            if (rIn) return true; // 빨간 구슬만 빠지면 즉시 성공

            // 동시에 굴렸는데 같은 위치에 멈춘 경우 (중요!)
            if (nrr == nbr && nrc == nbc) {
                // 더 많이 이동한 구슬(즉, 뒤에 있었던 구슬)을 한 칸 뒤로
                if (rDist > bDist) {
                    nrr -= dr[i]; nrc -= dc[i];
                } else {
                    nbr -= dr[i]; nbc -= dc[i];
                }
            }

            // 다음 재귀 호출
            if (recursive(new int[]{nrr, nrc}, new int[]{nbr, nbc}, count + 1)) return true;
        }

        // 탐색이 끝나면 다른 경로를 위해 방문 체크 해제 (Backtracking)
        visited[redBall[0]][redBall[1]][blueBall[0]][blueBall[1]] = false;
        return false;
    }
}