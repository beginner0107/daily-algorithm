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
        queue.add(new int[]{(1 << 0), 1, 0});
        boolean[] visited = new boolean[1 << len];
        visited[1 << 0] = true;
        
        while(!queue.isEmpty()) {
            int[]arr = queue.poll();
            int mask = arr[0];
            int sheep = arr[1];
            int wolf = arr[2];
            
            answer = Math.max(answer, sheep);
            
            for (int i = 0; i < len; i++) {
                if (((1 << i) & mask) != 0) {
                    for (int num : list.get(i)) {
                        int nextMask = (1 << num) | mask;
                        if (!visited[nextMask]) {
                            int nextSheep = sheep + (info[num] == 0 ? 1 : 0);
                            int nextWolf = wolf + (info[num] == 1 ? 1 : 0);
                            if (nextSheep > nextWolf) {
                                visited[nextMask] = true;
                                queue.add(new int[]{nextMask, nextSheep, nextWolf});
                            }
                        }
                    }
                }
            }
        }
        
        return answer;
    }
}