import java.util.*;
class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        
        Map<String, Integer> map = new HashMap<>();
        Map<String, HashSet<String>> map2 = new HashMap<>();

        for (String str : id_list) map.put(str, 0);
        
        for (String rpt : report) {
            String[] str = rpt.split(" ");
            if (map2.containsKey(str[1])) {
                map2.get(str[1]).add(str[0]);
                map.put(str[1], map2.get(str[1]).size());
            } else {
                HashSet<String> hs = new HashSet<>();
                hs.add(str[0]);
                map2.put(str[1], hs);
                map.put(str[1], 1);
            }
        }
        List<String> reportUser = new ArrayList<>();
        for (String key : map.keySet()) {
            if (map.get(key) >= k) {
                reportUser.add(key);
            }
        }
        
        Map<String, Integer> ans = new HashMap<>();
        for (int i = 0; i < id_list.length; i++) {
            if (reportUser.contains(id_list[i])) {
                HashSet<String> hs = map2.get(id_list[i]);
                for (String h : hs) {
                    ans.put(h, ans.getOrDefault(h, 0) + 1);
                }
            }
        }
        for (int i = 0; i < id_list.length; i++) {
            if (ans.containsKey(id_list[i])) {
                answer[i] = ans.get(id_list[i]);
            }
        }
        
        return answer;
    }
}