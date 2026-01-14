// Problem: 841. Keys and Rooms (Medium)
// Link: https://leetcode.com/problems/keys-and-rooms

import java.util.*;
class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        
        bfs(0, rooms, visited);
        
        for (boolean isVisited : visited) {
            if (!isVisited) {
                return false; 
            }
        }
    
        return true;
    }

    private void bfs(int start, List<List<Integer>> rooms, boolean[] visited) {
        Queue<Integer> queue = new ArrayDeque<>();
        
        queue.add(start);
        visited[start] = true;
        
        while (!queue.isEmpty()) {
            int currentRoom = queue.poll();
            
            for (int key : rooms.get(currentRoom)) {
                if (!visited[key]) {
                    visited[key] = true; 
                    queue.add(key);    
                }
            }
        }
    }
}