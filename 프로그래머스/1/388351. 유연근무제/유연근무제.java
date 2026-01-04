import java.util.*;
class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        
        for (int i = 0; i < timelogs.length; i++) {
            boolean islate = false;
            int hour = schedules[i] / 100;
            int min = schedules[i] % 100 + 10;
            if (min >= 60) {
                hour++;
                min -= 60;
            }
            int limit = hour * 100 + min;
            for (int j = 0; j < timelogs[i].length; j++) {
                if (startday == 1 && (j == 5 || j == 6)) continue; 
                if (startday == 2 && (j == 4 || j == 5)) continue; 
                if (startday == 3 && (j == 3 || j == 4)) continue; 
                if (startday == 4 && (j == 2 || j == 3)) continue; 
                if (startday == 5 && (j == 1 || j == 2)) continue; 
                if (startday == 6 && (j == 0 || j == 1)) continue; 
                if (startday == 7 && (j == 6 || j == 0)) continue; 

                if (limit < timelogs[i][j]) {
                    islate = true;
                    break;
                }
            }
            if (!islate) answer++;
        }
        
        return answer;
    }
}