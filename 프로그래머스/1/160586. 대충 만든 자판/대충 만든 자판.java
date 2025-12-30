import java.util.*;
class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        int[] result = new int[targets.length];
        int[] arr = new int[27];
        Arrays.fill(arr, Integer.MAX_VALUE);
        for (String k : keymap) {
            int idx = 0;
            for (char c : k.toCharArray()) {
                arr[c - 'A'] = Math.min(idx + 1, arr[c - 'A']);
                idx++;
            }
        }

        int index = 0;
        for (String t : targets) {
            boolean isOut = false;
            for (char c : t.toCharArray()) {
                if (arr[c - 'A'] == Integer.MAX_VALUE) {
                    isOut = true;
                    break;
                }
                result[index] += arr[c - 'A'];
            }
            if (isOut) result[index] = -1;
            index++;
        }
        return result;
    }
}