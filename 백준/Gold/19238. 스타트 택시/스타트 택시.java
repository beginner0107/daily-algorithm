import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map, passengerIdMap;
    static int[][] visited;
    static int visitToken = 0;

    static Taxi taxi;
    static Passenger[] passengerData;

    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        if (!setup()) return;

        for (int i = 0; i < M; i++) {
            if (!driveOneCycle()) {
                System.out.println("-1");
                return;
            }
        }
        System.out.println(taxi.fuel);
    }

    private static boolean driveOneCycle() {
        // 1. 승객 찾기 (PQ를 활용한 조기 종료 BFS)
        int[] target = findNearestPassenger();
        if (target == null) return false;

        int pId = target[0];
        int distToStart = target[1];
        Passenger p = passengerData[pId];

        if (taxi.fuel < distToStart) return false;

        taxi.fuel -= distToStart;
        taxi.r = p.sr; taxi.c = p.sc;
        passengerIdMap[p.sr][p.sc] = 0;

        // 2. 목적지로 이동
        int distToDest = getDistance(p.sr, p.sc, p.er, p.ec);
        if (distToDest == -1 || taxi.fuel < distToDest) return false;

        taxi.fuel += distToDest; // 보너스 (연료 - d + 2d)
        taxi.r = p.er; taxi.c = p.ec;

        return true;
    }

    private static int[] findNearestPassenger() {
        // 제자리 체크
        if (passengerIdMap[taxi.r][taxi.c] > 0)
            return new int[]{passengerIdMap[taxi.r][taxi.c], 0};

        visitToken++;
        // 거리 -> 행 -> 열 순서로 자동 정렬되는 우선순위 큐
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) ->
                a[2] != b[2] ? a[2] - b[2] : (a[0] != b[0] ? a[0] - b[0] : a[1] - b[1])
        );

        pq.add(new int[]{taxi.r, taxi.c, 0});
        visited[taxi.r][taxi.c] = visitToken;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();

            // 연료보다 먼 거리는 탐색 중단
            if (curr[2] > taxi.fuel) return null;

            // PQ를 썼으므로 가장 먼저 발견된 승객이 무조건 최우선순위임
            if (passengerIdMap[curr[0]][curr[1]] > 0) {
                return new int[]{passengerIdMap[curr[0]][curr[1]], curr[2]};
            }

            for (int i = 0; i < 4; i++) {
                int nr = curr[0] + dr[i], nc = curr[1] + dc[i];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N ||
                        map[nr][nc] == 1 || visited[nr][nc] == visitToken) continue;

                visited[nr][nc] = visitToken;
                pq.add(new int[]{nr, nc, curr[2] + 1});
            }
        }
        return null;
    }

    private static int getDistance(int sr, int sc, int er, int ec) {
        if (sr == er && sc == ec) return 0;
        visitToken++;

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sr, sc, 0});
        visited[sr][sc] = visitToken;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            if (curr[2] >= taxi.fuel) return -1; // 목적지 가기 전 연료 고갈

            for (int i = 0; i < 4; i++) {
                int nr = curr[0] + dr[i], nc = curr[1] + dc[i];
                if (nr == er && nc == ec) return curr[2] + 1;

                if (nr < 0 || nr >= N || nc < 0 || nc >= N ||
                        map[nr][nc] == 1 || visited[nr][nc] == visitToken) continue;

                visited[nr][nc] = visitToken;
                q.add(new int[]{nr, nc, curr[2] + 1});
            }
        }
        return -1;
    }

    private static boolean setup() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        long f = Long.parseLong(st.nextToken());

        map = new int[N][N];
        passengerIdMap = new int[N][N];
        visited = new int[N][N]; // 한 번만 생성

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        taxi = new Taxi(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, f);

        passengerData = new Passenger[M + 1];
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int sr = Integer.parseInt(st.nextToken()) - 1;
            int sc = Integer.parseInt(st.nextToken()) - 1;
            int er = Integer.parseInt(st.nextToken()) - 1;
            int ec = Integer.parseInt(st.nextToken()) - 1;
            passengerData[i] = new Passenger(i, sr, sc, er, ec);
            passengerIdMap[sr][sc] = i;
        }
        return true;
    }

    static class Taxi {
        int r, c; long fuel;
        Taxi(int r, int c, long f) { this.r = r; this.c = c; this.fuel = f; }
    }

    static class Passenger {
        int id, sr, sc, er, ec;
        Passenger(int id, int sr, int sc, int er, int ec) {
            this.id = id; this.sr = sr; this.sc = sc; this.er = er; this.ec = ec;
        }
    }
}