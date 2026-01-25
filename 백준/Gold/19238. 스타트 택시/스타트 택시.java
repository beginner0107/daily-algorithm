import java.io.IOException;
import java.util.ArrayDeque;

public class Main {
    static int N, M;
    static int[][] map, pid;
    static int[][] vis;
    static int vt = 0;

    static int tr, tc;
    static long fuel;
    static int[] psr, psc, per, pec;

    public static void main(String[] args) throws IOException {
        setup();

        for (int i = 0; i < M; i++) {
            int[] res = findPassenger();
            if (res == null) { System.out.println(-1); return; }

            int id = res[0], d1 = res[1];
            if (fuel < d1) { System.out.println(-1); return; }

            fuel -= d1;
            tr = psr[id]; tc = psc[id];
            pid[tr][tc] = 0;

            int d2 = getDist(tr, tc, per[id], pec[id]);
            if (d2 < 0 || fuel < d2) { System.out.println(-1); return; }

            fuel += d2;
            tr = per[id]; tc = pec[id];
        }
        System.out.println(fuel);
    }

    static int[] findPassenger() {
        if (pid[tr][tc] > 0) return new int[]{pid[tr][tc], 0};

        vt++;
        ArrayDeque<int[]> q = new ArrayDeque<>();
        int bestId = 0, bestR = N + 1, bestC = N + 1, bestD = -1;

        q.add(new int[]{tr, tc});
        vis[tr][tc] = vt;
        int dist = 0;

        while (!q.isEmpty()) {
            if (dist > fuel) break;

            int sz = q.size();
            while (sz-- > 0) {
                int[] c = q.poll();
                int r = c[0], cc = c[1];

                if (pid[r][cc] > 0) {
                    if (bestD < 0 || r < bestR || (r == bestR && cc < bestC)) {
                        bestId = pid[r][cc]; bestR = r; bestC = cc; bestD = dist;
                    }
                }

                if (r > 0 && map[r - 1][cc] == 0 && vis[r - 1][cc] != vt) {
                    vis[r - 1][cc] = vt; q.add(new int[]{r - 1, cc});
                }
                if (r < N - 1 && map[r + 1][cc] == 0 && vis[r + 1][cc] != vt) {
                    vis[r + 1][cc] = vt; q.add(new int[]{r + 1, cc});
                }
                if (cc > 0 && map[r][cc - 1] == 0 && vis[r][cc - 1] != vt) {
                    vis[r][cc - 1] = vt; q.add(new int[]{r, cc - 1});
                }
                if (cc < N - 1 && map[r][cc + 1] == 0 && vis[r][cc + 1] != vt) {
                    vis[r][cc + 1] = vt; q.add(new int[]{r, cc + 1});
                }
            }

            if (bestD >= 0) return new int[]{bestId, bestD};
            dist++;
        }
        return bestD >= 0 ? new int[]{bestId, bestD} : null;
    }

    static int getDist(int sr, int sc, int er, int ec) {
        if (sr == er && sc == ec) return 0;
        vt++;

        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sr, sc});
        vis[sr][sc] = vt;
        int dist = 0;

        while (!q.isEmpty()) {
            dist++;
            if (dist > fuel) return -1;

            int sz = q.size();
            while (sz-- > 0) {
                int[] c = q.poll();
                int r = c[0], cc = c[1];

                if (r > 0 && map[r - 1][cc] == 0 && vis[r - 1][cc] != vt) {
                    if (r - 1 == er && cc == ec) return dist;
                    vis[r - 1][cc] = vt; q.add(new int[]{r - 1, cc});
                }
                if (r < N - 1 && map[r + 1][cc] == 0 && vis[r + 1][cc] != vt) {
                    if (r + 1 == er && cc == ec) return dist;
                    vis[r + 1][cc] = vt; q.add(new int[]{r + 1, cc});
                }
                if (cc > 0 && map[r][cc - 1] == 0 && vis[r][cc - 1] != vt) {
                    if (r == er && cc - 1 == ec) return dist;
                    vis[r][cc - 1] = vt; q.add(new int[]{r, cc - 1});
                }
                if (cc < N - 1 && map[r][cc + 1] == 0 && vis[r][cc + 1] != vt) {
                    if (r == er && cc + 1 == ec) return dist;
                    vis[r][cc + 1] = vt; q.add(new int[]{r, cc + 1});
                }
            }
        }
        return -1;
    }

    static int idx, bufLen;
    static byte[] buf = new byte[1 << 17];

    static int read() throws IOException {
        int n = 0;
        byte b;
        // 숫자가 나올 때까지 스킵
        do {
            if (idx >= bufLen) {
                bufLen = System.in.read(buf);
                idx = 0;
                if (bufLen <= 0) return n;
            }
            b = buf[idx++];
        } while (b < '0' || b > '9');
        
        // 숫자 파싱
        do {
            n = n * 10 + (b - '0');
            if (idx >= bufLen) {
                bufLen = System.in.read(buf);
                idx = 0;
                if (bufLen <= 0) return n;
            }
            b = buf[idx++];
        } while (b >= '0' && b <= '9');
        
        return n;
    }

    static void setup() throws IOException {
        N = read(); M = read(); fuel = read();

        map = new int[N][N];
        pid = new int[N][N];
        vis = new int[N][N];

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                map[i][j] = read();

        tr = read() - 1; tc = read() - 1;

        psr = new int[M + 1]; psc = new int[M + 1];
        per = new int[M + 1]; pec = new int[M + 1];

        for (int i = 1; i <= M; i++) {
            psr[i] = read() - 1; psc[i] = read() - 1;
            per[i] = read() - 1; pec[i] = read() - 1;
            pid[psr[i]][psc[i]] = i;
        }
    }
}