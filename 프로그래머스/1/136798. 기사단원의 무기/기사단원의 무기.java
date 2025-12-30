class Solution {
    public int solution(int number, int limit, int power) {
        int answer = 0;
        int[] count = new int[number + 1];
        for (int i = 1; i <= number; i++) {
            for (int j = i; j <= number; j += i) {
                count[j]++; 
            }
        }
        
        for (int i = 1; i < count.length; i++) {
            answer += isOverhead(count[i], limit) ? power : count[i];
        }
        return answer;
    }
    
    private boolean isOverhead(int c, int l) {
        return c > l;
    }
}