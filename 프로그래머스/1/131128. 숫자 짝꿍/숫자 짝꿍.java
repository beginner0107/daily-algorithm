import java.util.*;

class Solution {
    public String solution(String X, String Y) {
        int[] arr = new int[10];
        int[] arr2 = new int[10];
        
        for (int i = 0; i < X.length(); i++) {
            arr[X.charAt(i) - '0']++;
        }
        for (int i = 0; i < Y.length(); i++) {
            arr2[Y.charAt(i) - '0']++;
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 9; i >= 0; i--) {
            int count = arr[i];
            int count2 = arr2[i];
            
            int s = Math.min(count, count2); 
            
            for (int j = 0; j < s; j++) {
                sb.append(i);
            }
        }
        
        if (sb.length() == 0) return "-1";
        if (sb.charAt(0) == '0') return "0";
        
        return sb.toString();
    }
}