class Solution {
    public int solution(String s) {
        int minVal = s.length();
        for (int i = 1; i <= s.length() / 2; i++) {
            String pattern = s.substring(0, i); // 초기 패턴
            int count = 1;
            StringBuilder sb = new StringBuilder();
            int k = 0;
            for (int j = i; j < s.length(); j += i) {
                if (j + i >= s.length()) {
                    if (pattern.equals(s.substring(j, s.length()))) {
                        sb.append(count + 1).append(pattern);
                    } else {
                        sb.append(count == 1 ? "" : count).append(pattern);
                        sb.append(s.substring(j, s.length()));
                    }
                    break;
                }
                String newPattern = s.substring(j, j + i);
                if (pattern.equals(newPattern)) {
                    count++;
                } else {
                    sb.append(count == 1 ? "" : count).append(pattern);
                    count = 1;
                    pattern = newPattern;
                }
            }
            minVal = Math.min(minVal, sb.length());
        }
        return minVal;
    }
}