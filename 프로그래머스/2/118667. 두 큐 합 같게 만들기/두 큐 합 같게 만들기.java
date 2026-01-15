import java.util.*;
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();

        long sum1 = 0;
        long sum2 = 0;
        for (int i = 0; i < queue1.length; i++) {
            int num1 = queue1[i];
            int num2 = queue2[i];
            sum1 += num1;
            sum2 += num2;
            q1.add(num1);
            q2.add(num2);
        }

        int n = 0;
        int max = (q1.size() + q2.size()) * 2;
        while(!q1.isEmpty() && !q2.isEmpty() && max >= n) {
            if (sum1 == sum2) return n;
            if (sum1 > sum2) {
                int num = q1.poll();
                q2.add(num);
                sum1 -= num;
                sum2 += num;
            } else {
                int num = q2.poll();
                q1.add(num);
                sum2 -= num;
                sum1 += num;
            }
            n++;
        }
        
        return -1;
    }
}
