import java.util.*;
class Solution {
    // 이거 날짜를 일로 계산하면 될듯
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> ans = new ArrayList<>();
        int parseToday = dateToSec(today);
        
        // 아예 달수를 일로 바꿔서 넣어주고
        Map<Character, Integer> map = new HashMap<>();
        for (String tm : terms) {
            String[] arr = tm.split(" ");
            map.put(arr[0].charAt(0), Integer.parseInt(arr[1]) * 28);
        }
        int st = 1;
        for (String pv : privacies) {
            String arr[] = pv.split(" ");
            String ymd = arr[0];
            char tm = arr[1].charAt(0);
            int pvTime = dateToSec(ymd) + map.get(tm); // 약관 최대 일자
            if (parseToday >= pvTime) {
                ans.add(st);
            }
            st++;
        }
        int[] answer = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            answer[i] = ans.get(i);
        }
        return answer;
    }
    
    public int dateToSec(String date) {
        String[] str = date.split("\\.");
        int year = Integer.parseInt(str[0]) * 12 * 28;
        int month = Integer.parseInt(str[1]) * 28;
        int day = Integer.parseInt(str[2]);
        return year + month + day;
    }
}