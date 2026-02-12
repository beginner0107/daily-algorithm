// Problem: 322. Coin Change (Medium)
// Link: https://leetcode.com/problems/coin-change

import java.util.*;
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] arr = new int[amount + 1];
        int INF = amount + 1;
        Arrays.fill(arr, INF);
        arr[0] = 0;
        for (int i = 1; i <= amount; i++) {
            
            for (int coin : coins) {
                if (i - coin < 0) continue;
                arr[i] = Math.min(arr[i - coin] + 1, arr[i]);
            }
        }

        return arr[amount] == INF ? -1 : arr[amount];
    }
}