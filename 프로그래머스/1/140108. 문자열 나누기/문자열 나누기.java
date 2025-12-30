class Solution {
    public int solution(String s) {
        int answer = 0;
        int cnt1 = 0;
        int cnt2 = 0;
        char c = ' ';
        for (int i = 0; i < s.length(); i++) {
            if (cnt1 == cnt2) {
                answer++;
                c = s.charAt(i);
            }
            if (c == s.charAt(i)) {
                cnt1++;
            } else {
                cnt2++;
            }
        }
        return answer;
    }
}