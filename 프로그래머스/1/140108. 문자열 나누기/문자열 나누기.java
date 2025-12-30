class Solution {
    public int solution(String s) {
        int answer = 0;
        int cnt1 = 0;
        int cnt2 = 0;
        char c = '0';
        for (int i = 0; i < s.length(); i++) {
            if (cnt1 == 0) {
                c = s.charAt(i);
                cnt1++;
                continue;
            } else {
                if (c == s.charAt(i)) {
                    cnt1++;
                } else {
                    cnt2++;
                }
                if (cnt1 == cnt2) {
                    cnt1 = 0;
                    cnt2 = 0;
                    c = '0';
                    answer++;
                }
            }
        }
        if (c != '0') answer++;
        return answer;
    }
}