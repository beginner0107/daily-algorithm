// Problem: 322. Coin Change (Medium)
// Link: https://leetcode.com/problems/coin-change

import java.util.*;

class Solution {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        boolean visited[] = new boolean[amount + 1];

        Queue<Integer> queue = new ArrayDeque<>();
        for (int coin : coins) {
            if (coin == amount) return 1;
            if (coin > amount) continue;
            queue.add(coin);
            visited[coin] = true;
        }

        int answer = 1;
        while (!queue.isEmpty()) {
            int qSize = queue.size();
            for (int i = 0; i < qSize; i++) {
                Integer n = queue.poll();
                visited[n] = true;
                for (int coin : coins) {
                    if (n + coin > amount) continue;
                    if (n + coin == amount) return ++answer;
                    if (!visited[n + coin]) {
                        visited[n + coin] = true;
                        queue.add(n + coin);
                    }
                }
            }
            answer++;
        }

        return -1;
    }
}