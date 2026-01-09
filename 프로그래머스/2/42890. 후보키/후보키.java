import java.util.*;

class Solution {
    public int solution(String[][] relation) {
        Set<Integer> candidateKeys = new HashSet<>();
        int rowLen = relation.length;
        int colLen = relation[0].length;
        for (int i = 1; i < (1 << colLen); i++) {
            if (checkMinimality(i, candidateKeys)) {
                if (checkUniqueness(i, relation, rowLen, colLen)) {
                    candidateKeys.add(i);
                }
            }
        }
        return candidateKeys.size();
    }
    
    private boolean checkMinimality(int i, Set<Integer> candidateKeys) {
        for (int key : candidateKeys) {
            if ((key & i) == key) return false;
        }
        return true;
    }
    
    private boolean checkUniqueness(int i, String[][]relation, int rowLen, int colLen) {
        Set<String> set = new HashSet<>();
        for (int row = 0; row < rowLen; row++) {
            StringBuilder sb = new StringBuilder();
            for (int col = 0; col < colLen; col++) {
                if ((i & (1 << col)) != 0) {
                    sb.append(relation[row][col]).append("/");
                }
            }
            set.add(sb.toString());
        }
        return set.size() == rowLen;
    }
}