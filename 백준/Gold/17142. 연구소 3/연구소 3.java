import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int[][]map;
    static List<int[]> empties;
    static List<int[]> viruses;
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static int[][] distance;
    static int answer;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        answer = Integer.MAX_VALUE;
        empties = new ArrayList<>(); // 빈칸
        viruses = new ArrayList<>(); // 비활성 바이러스
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == 0) empties.add(new int[]{r, c});
                else if (map[r][c] == 2) {
                    viruses.add(new int[]{r, c});
                    map[r][c] = 3;
                }
            }
        }
        distance = new int[map.length][map[0].length];
        chooseVirus(0, 0);
        System.out.println(answer ==Integer.MAX_VALUE ? -1 : answer);
    }

    private static void chooseVirus(int start, int count) {
        if (count == M) {
            // 퍼트리기 전에 원본 배열을 복사한다.
            int[][] temp = createMap();
            int[][] tempDistance = new int[temp.length][temp[0].length];
            // 바이러스를 퍼트린다
            bfs(temp, tempDistance);
            // 바이러스가 다 전파되었는지 확인하고, 시간의 최소 값을 구한다.
            boolean isFull = true;
            int max = 0;
            for (int i = 0; i < empties.size(); i++) {
                int[] empty = empties.get(i);
                int row = empty[0];
                int col = empty[1];
                max = Math.max(tempDistance[row][col], max);
            }
            for (int i = 0; i < temp.length; i++) {
                for (int j = 0; j < temp[i].length; j++) {
                    if (temp[i][j] == 0){
                        isFull = false;
                        break;
                    }
                    if (!isFull) break;
                }
            }
            if (isFull) {
                answer = Math.min(answer, max);
            }
            return;
        }

        for (int i = start; i < viruses.size(); i++) {
            int[] virus = viruses.get(i);
            int row = virus[0];
            int col = virus[1];

            map[row][col] = 2;
            chooseVirus(i + 1, count + 1);
            map[row][col] = 3;
        }
    }

    private static void bfs(int[][] temp, int[][] tempDistance) {
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[i].length; j++) {
                if (temp[i][j] == 2) {
                    queue.add(new int[]{i, j});
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] virus = queue.poll();
            int row = virus[0];
            int col = virus[1];

            for (int i = 0; i < 4; i++) {
                int nr = row + dr[i];
                int nc = col + dc[i];
                if (nr >= 0 && nr < temp.length && nc >= 0 && nc < temp[0].length) {
                    if (temp[nr][nc] == 0 || temp[nr][nc] == 3) {
                        tempDistance[nr][nc] = tempDistance[row][col] + 1;
                        temp[nr][nc] = 2;

                        queue.add(new int[]{nr, nc});
                    }
                }
            }
        }
    }

    private static int[][] createMap() {
        int[][] temp = new int[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            System.arraycopy(map[i], 0, temp[i],0, map[i].length);
        }
        return temp;
    }
}