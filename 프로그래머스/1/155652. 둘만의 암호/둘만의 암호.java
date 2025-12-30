import java.util.*;
class Solution {
    public String solution(String s, String skip, int index) {
        String answer = "";
        Set<Character> hs = new HashSet<>();
        for (char c : skip.toCharArray()) {
            hs.add(c);
        }
        for (char c : s.toCharArray()) {
            int count = index;
            while (count > 0) {
                c++; 
                if (c > 'z') {
                    c = 'a';
                }

                if (hs.contains(c)) {
                    continue; 
                }

                count--; 
            }
            answer += String.valueOf(c);
        }
        
        return answer;
    }
}