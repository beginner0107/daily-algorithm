import java.util.*;
class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        int[] result = new int[targets.length];
        Map<Character, Integer> map = new HashMap<>();

        for (String k : keymap) {
            int si = 1;
            for (char c : k.toCharArray()) {
                if (map.containsKey(c)) {
                    if (si <= map.get(c)) {
                        map.put(c, si);
                    }
                } else {
                    map.put(c, si);
                }
                si++;
            }
        }

        int index = 0;
        for (String t : targets) {
            int r = 0;
            for (char c : t.toCharArray()) {
                if (map.containsKey(c)) {
                    r += map.get(c);
                } else {
                    r = 0;
                    break;
                }
            }
            if (r == 0) result[index] = -1;
            else result[index] = r;
            index++;
        }
        return result;
    }
}