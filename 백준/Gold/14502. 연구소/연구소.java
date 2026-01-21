import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;

    // 좌표 리스트: "탐색 대상을 미리 고정"하면 코드가 쉬워짐
    static List<int[]> empties = new ArrayList<>();
    static List<int[]> viruses = new ArrayList<>();

    static int maxSafe = 0;

    static final int[] dr = {1, -1, 0, 0};
    static final int[] dc = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == 0) empties.add(new int[]{r, c});
                else if (map[r][c] == 2) viruses.add(new int[]{r, c});
            }
        }

        chooseWalls(0, 0);

        System.out.println(maxSafe);
    }

    /**
     * 조합 DFS: empties 중 3개를 중복 없이 고름
     *
     * start: 다음 선택 시작 index (조합 중복 제거 핵심)
     * count: 현재까지 세운 벽 개수
     */
    static void chooseWalls(int start, int count) {
        if (count == 3) {
            maxSafe = Math.max(maxSafe, simulateAndCountSafe());
            return;
        }

        for (int i = start; i < empties.size(); i++) {
            int[] empty = empties.get(i);
            int r = empty[0];
            int c = empty[1];
            map[r][c] = 1;
            chooseWalls(i + 1, count + 1);
            map[r][c] = 0;
        }
    }

    /**
     * 현재 map(벽 3개가 세워진 상태)을 기준으로:
     * 1) temp로 복사
     * 2) temp에서 바이러스 전파
     * 3) temp에서 0 개수 세기
     */
    static int simulateAndCountSafe() {
        int[][] temp = copyMap(map);
        spreadVirusBfs(temp);
        return countZeros(temp);
    }

    /**
     * BFS 전파:
     * - viruses의 모든 좌표를 큐에 넣고 시작 (multi-source BFS)
     * - 4방향으로 temp[nr][nc]==0이면 2로 바꾸고 큐에 넣기
     */
    static void spreadVirusBfs(int[][] temp) {
        Queue<int[]> q = new ArrayDeque<>();
        for (int[] virus : viruses) {
            q.add(new int[]{virus[0], virus[1]});
        }
        while (!q.isEmpty()) {
            int[] virus = q.poll();
            int row = virus[0];
            int col = virus[1];
            for (int i = 0; i < 4; i++) {
                int nextRow = row + dr[i];
                int nextCol = col + dc[i];
                if (nextRow >= 0 && nextRow < temp.length && nextCol >= 0 && nextCol < temp[0].length) {
                    if (temp[nextRow][nextCol] == 0) {
                        temp[nextRow][nextCol] = 2;
                        q.offer(new int[]{nextRow, nextCol});
                    }
                }
            }
        }
    }

    /**
     * temp에서 0(빈칸) 개수를 세면 안전영역 크기
     */
    static int countZeros(int[][] temp) {
        int count = 0;
        for (int [] tp : temp) {
            for (int t : tp) {
                if (t == 0) count++;
            }
        }
        return count;
    }

    /**
     * map 복사 (깊은 복사)
     */
    static int[][] copyMap(int[][] src) {
        int[][] temp = new int[src.length][src[0].length];
        for (int i = 0; i < src.length; i++) {
            System.arraycopy(src[i], 0, temp[i], 0, src[0].length);
        }
        return temp;
    }
}
