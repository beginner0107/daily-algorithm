// Problem: 131. Palindrome Partitioning (Medium)
// Link: https://leetcode.com/problems/palindrome-partitioning

import java.util.*;
class Solution {
    List<List<String>> answer;
    public List<List<String>> partition(String s) {
        answer = new ArrayList<>();

        backtrack(s, 0, new ArrayList<>());
        return answer;
    }

    private void backtrack(String s, int start, List<String> al) {
        if (s.length() == start) {
            answer.add(new ArrayList<>(al));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (isPalindrome(s, start, i)) {
                al.add(s.substring(start, i + 1));
                backtrack(s, i + 1, al);
                al.remove(al.size() -1);
            }
        }
    }

    private boolean isPalindrome(String s, int lt, int rt) {
        while(lt < rt) {
            if (s.charAt(lt) != s.charAt(rt)) return false;
            lt ++;
            rt --;
        }
        return true;
    }
}