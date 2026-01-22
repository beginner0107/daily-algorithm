import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static char[][] map;
    // 방문 체크: [빨강행][빨강열][파랑행][파랑열]
    static boolean[][][][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N][M][N][M];
        map = new char[N][M];
        int redRow = 0, redCol = 0, blueRow = 0, blueCol = 0;

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'R') { redRow = i; redCol = j; }
                if (map[i][j] == 'B') { blueRow = i; blueCol = j; }
            }
        }

        if (canEscape(redRow, redCol, blueRow, blueCol, 1)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    private static boolean canEscape(int rRow, int rCol, int bRow, int bCol, int count) {
        // 10번이 넘어가면 실패
        if (count > 10) return false;
        if (visited[rRow][rCol][bRow][bCol]) return false;
        visited[rRow][rCol][bRow][bCol] = true;

        for (int i = 0; i < 4; i++) {
            int drR = dr[i];
            int drC = dc[i];

            int nrr = rRow, nrc = rCol;
            int nbr = bRow, nbc = bCol;
            int rDist = 0, bDist = 0;
            boolean rIn = false, bIn = false;

            // 빨간 구슬 미끄러지기
            for (int k = 1; k <= Math.max(M, N); k++) {
                if (map[nrr + drR][nrc + drC] == '#') break;
                nrr += drR;
                nrc += drC;
                rDist++;
                if (map[nrr][nrc] == 'O') {
                    rIn = true;
                    break;
                }
            }

            for (int k = 1; k <= Math.max(M, N); k++) {
                if (map[nbr + drR][nbc + drC] == '#') break;
                nbr += drR;
                nbc += drC;
                bDist++;
                if (map[nbr][nbc] == 'O') {
                    bIn = true;
                    break;
                }
            }

            if (bIn) continue; // b가 들어갔으면 안 됨
            if (rIn) return true; // b가 안 들어갔을 때 r이 들어가면 굿

            // b랑 a 모두 들어가지 못 했을 때
            // 같은 좌표에 위치하고 있을 때
            // 좌표를 수정해야 함.
            if (nrr == nbr && nrc == nbc) {
                if (rDist < bDist) { // b가 더 뒤에 있었다는 뜻
                    // b의 좌표를 한칸 뒤로 미루자
                    nbr -= drR;
                    nbc -= drC;
                } else {
                    nrr -= drR;
                    nrc -= drC;
                }
            }
            if (canEscape(nrr, nrc, nbr, nbc, count + 1)) {
                return true;
            }
        }
        visited[rRow][rCol][bRow][bCol] = false;
        return false;
    }
}
