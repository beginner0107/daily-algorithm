class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        int cards1Index = 0;
        int cards2Index = 0;
        int cards1Len = cards1.length;
        int cards2Len = cards2.length;
        
        for (int i = 0; i < goal.length; i++) {
            if ((cards1Index < cards1Len && cards1[cards1Index].length() < 1) || (cards2Index < cards2Len && cards2[cards2Index].length() > 10)) {
                return "No";
            }
            if (cards1Index < cards1Len && goal[i].equals(cards1[cards1Index])) {
                cards1Index++;
            }
            else if (cards2Index < cards2Len && goal[i].equals(cards2[cards2Index])) {
                cards2Index++;
            } else {
                return "No";
            }
            
        }
        return "Yes";
    }
}