class Solution {
    public int solution(int number, int limit, int power) {
        int answer = 0;
        for (int i = 1; i <= number; i++) {
            int count = getDivision(i);
            answer += isOverhead(count, limit) ? power : count;
        }
        return answer;
    }
    
    private boolean isOverhead(int c, int l) {
        return c > l;
    }
    
    private int getDivision(int n) {
        int count = 0;
        for (int i = 1; i * i <= n; i ++) {
            if (n % i == 0) {
                if (i * i == n) {
                    count += 1;
} else {
                    count += 2;
                }
            }
        }
        return count;
    }
}