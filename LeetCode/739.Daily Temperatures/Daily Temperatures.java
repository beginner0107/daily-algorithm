// Problem: 739. Daily Temperatures (Medium)
// Link: https://leetcode.com/problems/daily-temperatures

import java.util.*;
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] answer = new int[temperatures.length ];
        for (int i = 0; i < temperatures.length; i++) {

            int oldDay;
            while(!stack.isEmpty() &&  temperatures[i] > temperatures[stack.peek()]) {
                oldDay = stack.pop();
                answer[oldDay] = i - oldDay;
            }
            stack.push(i);
        }

        return answer;
    }
}