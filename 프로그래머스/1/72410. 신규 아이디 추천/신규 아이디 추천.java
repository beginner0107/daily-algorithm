/**
아이디의 길이는 3자 이상 15자 이하

아이디는 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.) 문자만 사용할 수 있습니다.

단, 마침표(.)는 처음과 끝에 사용할 수 없으며 또한 연속으로 사용할 수 없습니다.

1단계 new_id의 모든 대문자를 대응되는 소문자로 치환합니다.
2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
     만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
**/
class Solution {
    public String solution(String new_id) {
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < new_id.length(); i++) {
            char num = new_id.charAt(i);
            if (num <= 90 && num >= 65) {
                id.append((char) (num + 32));
            } else if (num == '-' || num == '_' || num == '.'){
                id.append(num);
            } else if (num >= 97 && num <= 122) {
                id.append(num);
            } else if (Character.isDigit(num)) {
                id.append(num);
            }
        }
        
        StringBuilder nid = new StringBuilder();
        for (int i = 0; i < id.length(); i++) {
            if (id.charAt(i) == '.') {
                int idx = i;
                while (idx + 1 < id.length() && id.charAt(idx + 1) == '.') {
                    idx++;
                }
                if (idx != i) {
                    i = idx;
                    nid.append('.');
                } else {
                    nid.append('.');
                }
            } else {
                nid.append(id.charAt(i));
            }
        }
        
        if (nid.length() > 0 && nid.charAt(0) == '.') {
            nid.replace(0, 1, "");
        }
        if (nid.length() > 0 && nid.charAt(nid.length() - 1) == '.') {
            nid.replace(nid.length() - 1, nid.length(), "");
        }
        if (nid.length() == 0) {
            nid.append("a");
        }
        StringBuilder str = new StringBuilder(nid.toString());
        if (nid.length() >= 15) {
            str = new StringBuilder(nid.substring(0, 15));
        }
        String ans = str.toString();
        if (str.length() != 0 && str.charAt(str.length() - 1) == '.') {
            ans = ans.substring(0, str.length() - 1);
        }
        if (ans.length() != 0 && ans.length() <= 2) {
            char last = ans.charAt(ans.length() - 1);
            while (ans.length() < 3) {
                ans += last;
            }
        }

        
        return ans;
    }
}