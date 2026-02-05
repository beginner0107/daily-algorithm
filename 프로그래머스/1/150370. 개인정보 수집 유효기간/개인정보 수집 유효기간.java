import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        Map<String, Integer> termMap = new HashMap<>();
        for (String term : terms) {
            String[] termParts = term.split(" ");
            termMap.put(termParts[0], (Integer.valueOf(termParts[1]) * 28));
        }

        int todayTotalDays = toNumber(today);
        List<Integer> expiredIndices = new ArrayList<>();
        int index = 0;

        for (String privacy : privacies) {
            String[] privacyParts = privacy.split(" "); 
            
            int termDurationDays = termMap.get(privacyParts[1]);
            int privacyStartTotalDays = toNumber(privacyParts[0]) + termDurationDays;
            
            if (todayTotalDays >= privacyStartTotalDays) {
                expiredIndices.add(index);
            }
            index++;
        }
        
        Collections.sort(expiredIndices);
        int[] answer = new int[expiredIndices.size()];
        int answerIdx = 0;
        for (int expiredIdx : expiredIndices) {
            answer[answerIdx] = expiredIdx + 1;
            answerIdx++;
        }
        return answer;
    }
    
    public int toNumber(String yyyyMmdd) {
        String[] dateParts = yyyyMmdd.split("\\.");
        int year = Integer.valueOf(dateParts[0]);
        int month = Integer.valueOf(dateParts[1]);
        int day = Integer.valueOf(dateParts[2]);
        return (year * 12 * 28) + (month * 28) + day;
    }
}