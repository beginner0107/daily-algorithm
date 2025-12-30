import java.util.*;
class Solution {
    public String solution(String s, String skip, int index) {
        String answer = "";
        Set<Character> hs = new HashSet<>();
        for (char c : skip.toCharArray()) {
            hs.add(c);
        }
        for (char c : s.toCharArray()) {
            int id = index;
            while (id > 0) {
                if (!hs.contains(c)) {
                    id--;
                }
                
                
                if (c + 1 >= 123) {
                    c = 'a';
                    while(hs.contains(c)) {
                        if (c + 1 >= 123) c = 'a';
                        else c = (char)(c + 1);
                    }
                } else {
                    c = (char)(c + 1);
                    while(hs.contains(c)) {
                        if (c + 1 >= 123) c = 'a';
                        else c = (char)(c + 1);
                    }
                }
                
            }
            answer += String.valueOf(c);
        }
        
        return answer;
    }
}