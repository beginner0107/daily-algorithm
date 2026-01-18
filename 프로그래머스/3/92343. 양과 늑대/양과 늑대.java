import java.util.*;
class Solution {
    public int solution(int[] info, int[][] edges) {
        int answer = 0;
        int len = info.length;
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < len; i++) list.add(new ArrayList<>());
        for (int i = 0; i < edges.length; i++) {
            list.get(edges[i][0]).add(edges[i][1]);
        }
        
        Queue<int[]> queue = new ArrayDeque<>();
        // 장바구니, 양의 개수, 늑대의 개수
        queue.add(new int[]{1 << 0, 1, 0});
        boolean[] visitedState = new boolean[1 << len];
        visitedState[1 << 0] = true;

        while(!queue.isEmpty()) {
            int[] arr = queue.poll();
            int mask = arr[0];
            int sheep = arr[1];
            int wolf = arr[2];
            answer = Math.max(answer, sheep);
            
            for (int i = 0; i < len; i++) {
                // 만약 i번째 노드가 현재 방문한 상태(mask)라면?
                if ((mask & (1 << i)) != 0) {
                    for (int num:list.get(i)) {

                        int next = mask | (1 << num);
                        // 방문 한 곳인지 확인해야 한다..
                        if (!visitedState[next]) {
                            // 큐에 넣는다.
int nextSheep = sheep + (info[num] == 0 ? 1 : 0);
                            int nextWolf = wolf + (info[num] == 1 ? 1 : 0);
                            
                            // 늑대에게 잡히지 않는다면 큐에 추가!
                            if (nextSheep > nextWolf) {
                                visitedState[next] = true;
                                queue.add(new int[]{next, nextSheep, nextWolf});
                            }
                        }
                    }
                }
            }
        }
        
        return answer;
    }
}