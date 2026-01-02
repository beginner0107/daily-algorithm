class Solution {
    public String solution(String X, String Y) {
        int[] countX = new int[10];
        int[] countY = new int[10];

        for (char c : X.toCharArray()) countX[c - '0']++;
        for (char c : Y.toCharArray()) countY[c - '0']++;

        StringBuilder sb = new StringBuilder();

        for (int i = 9; i >= 0; i--) {
            int common = Math.min(countX[i], countY[i]);
            
            for (int j = 0; j < common; j++) {
                sb.append(i);
            }
        }

        String result = sb.toString();
        
        if (result.equals("")) return "-1";     
        if (result.startsWith("0")) return "0";  

        return result;
    }
}