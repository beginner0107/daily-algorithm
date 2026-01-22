import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static char[][] map;
    // 방문 체크: [빨강행][빨강열][파랑행][파랑열]
    static boolean[][][][] visited = new boolean[11][11][11][11];
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        int rx = 0, ry = 0, bx = 0, by = 0;

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'R') { rx = i; ry = j; }
                if (map[i][j] == 'B') { bx = i; by = j; }
            }
        }

        if (canEscape(rx, ry, bx, by, 1)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    private static boolean canEscape(int rx, int ry, int bx, int by, int count) {
        // 10번이 넘어가면 실패
        if (count > 10) return false;

        for (int i = 0; i < 4; i++) {
            int nrx = rx, nry = ry, nbx = bx, nby = by;
            boolean redIn = false, blueIn = false;

            // 빨간 구슬 이동
            while (map[nrx + dr[i]][nry + dc[i]] != '#') {
                nrx += dr[i];
                nry += dc[i];
                if (map[nrx][nry] == 'O') {
                    redIn = true;
                    break;
                }
            }

            // 파란 구슬 이동
            while (map[nbx + dr[i]][nby + dc[i]] != '#') {
                nbx += dr[i];
                nby += dc[i];
                if (map[nbx][nby] == 'O') {
                    blueIn = true;
                    break;
                }
            }

            // 파란 구슬이 빠지면 실패 (빨간 구슬과 동시에 빠져도 실패)
            if (blueIn) continue;

            // 빨간 구슬만 빠지면 성공!
            if (redIn) return true;

            // 두 구슬이 같은 위치일 때 처리 (겹침 방지)
            if (nrx == nbx && nry == nby) {
                // 각 방향별로 더 멀리서 온 구슬을 한 칸 뒤로
                int redDist = Math.abs(nrx - rx) + Math.abs(nry - ry);
                int blueDist = Math.abs(nbx - bx) + Math.abs(nby - by);

                if (redDist > blueDist) {
                    nrx -= dr[i]; nry -= dc[i];
                } else {
                    nbx -= dr[i]; nby -= dc[i];
                }
            }

            // 재귀 호출 (하나라도 성공하면 true 반환)
            if (canEscape(nrx, nry, nbx, nby, count + 1)) return true;
        }

        return false;
    }
}
