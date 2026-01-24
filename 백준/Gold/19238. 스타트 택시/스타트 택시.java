import java.util.*;
import java.io.*;

public class Main {
    static int N, M, fuel;
    static int[][] map;
    static int[][] passengerMap;
    static Passenger[] info;
    static int taxiR, taxiC;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Passenger {
        int id, destR, destC;
        public Passenger(int id, int dr, int dc) {
            this.id = id; this.destR = dr; this.destC = dc;
        }
    }

    static class Node implements Comparable<Node> {
        int r, c, dist;
        Node(int r, int c, int dist) { this.r = r; this.c = c; this.dist = dist; }

        @Override
        public int compareTo(Node o) {
            if (this.dist != o.dist) return Integer.compare(this.dist, o.dist);
            if (this.r != o.r) return Integer.compare(this.r, o.r);
            return Integer.compare(this.c, o.c);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        taxiR = Integer.parseInt(st.nextToken()) - 1;
        taxiC = Integer.parseInt(st.nextToken()) - 1;

        passengerMap = new int[N][N];
        info = new Passenger[M + 1];
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int sr = Integer.parseInt(st.nextToken()) - 1;
            int sc = Integer.parseInt(st.nextToken()) - 1;
            int dr = Integer.parseInt(st.nextToken()) - 1;
            int dc = Integer.parseInt(st.nextToken()) - 1;
            passengerMap[sr][sc] = i; // 승객 ID 저장
            info[i] = new Passenger(i, dr, dc);
        }

        for (int i = 0; i < M; i++) {
            // 1. 가장 가까운 승객 찾기
            Node target = findPassenger();
            if (target == null || fuel < target.dist) { System.out.println("-1"); return; }

            // 승객 태우러 이동 및 연료 차감
            fuel -= target.dist;
            int pId = passengerMap[target.r][target.c];
            passengerMap[target.r][target.c] = 0; // 태웠으니 맵에서 제거
            taxiR = target.r; taxiC = target.c;

            // 2. 목적지로 이동
            int distToDest = moveToDest(info[pId]);
            if (distToDest == -1 || fuel < distToDest) { System.out.println("-1"); return; }

            // 목적지 도착 후 연료 업데이트
            fuel += distToDest; // 소모량 차감 후 2배 충전 (결국 +소모량)
            taxiR = info[pId].destR;
            taxiC = info[pId].destC;
        }

        System.out.println(fuel);
    }

    static Node findPassenger() {
        PriorityQueue<Node> pq = new PriorityQueue<>(); // 큐에서 꺼낼 때 자동 정렬
        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        q.add(new Node(taxiR, taxiC, 0));
        visited[taxiR][taxiC] = true;

        int minDist = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            Node curr = q.poll();

            if (curr.dist > minDist) break;

            // 현재 칸에 승객이 있으면 후보 리스트(PQ)에 추가
            if (passengerMap[curr.r][curr.c] > 0) {
                minDist = curr.dist;
                pq.add(curr);
            }

            for (int i = 0; i < 4; i++) {
                int nr = curr.r + dr[i];
                int nc = curr.c + dc[i];
                if (nr < 0 || nc < 0 || nr >= N || nc >= N || map[nr][nc] == 1 || visited[nr][nc]) continue;
                visited[nr][nc] = true;
                q.add(new Node(nr, nc, curr.dist + 1));
            }
        }
        return pq.poll(); // 가장 우선순위 높은 승객 반환
    }

    static int moveToDest(Passenger p) {
        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        q.add(new Node(taxiR, taxiC, 0));
        visited[taxiR][taxiC] = true;

        while (!q.isEmpty()) {
            Node curr = q.poll();
            if (curr.r == p.destR && curr.c == p.destC) return curr.dist;

            for (int i = 0; i < 4; i++) {
                int nr = curr.r + dr[i];
                int nc = curr.c + dc[i];
                if (nr < 0 || nc < 0 || nr >= N || nc >= N || map[nr][nc] == 1 || visited[nr][nc]) continue;
                visited[nr][nc] = true;
                q.add(new Node(nr, nc, curr.dist + 1));
            }
        }
        return -1; // 목적지에 도달할 수 없는 경우
    }
}