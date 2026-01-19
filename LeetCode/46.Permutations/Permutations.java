// Problem: 46. Permutations (Medium)
// Link: https://leetcode.com/problems/permutations

/**
문제 파악

입출력
- 입력: 정수형 1차원 배열
- 출력: List<List<Integer>>
핵심규칙
- 모든 조합(combination)
- 순서는 상관 없음
제약사항
- nums 배열의 길이는 1~6입니다.
- nums[i]의 범위는 -10에서 10입니다.
접근 방법

알고리즘
- 백트래킹
자료구조
- boolean[] 배열(방문), List<List<Integer>>(정답), List<Integer>
의사코드
- 1. 인덱스를 방문하고 뎁스는 0
- 2. 방문 표시를 한다. visited[i] = true, List<Integer> 에 적재한다.
- 3. 다시 재귀 호출한다. 뎁스는 1
- 4. 방문하지 않은 곳을 방문한다. List<Integer> 에 적재한다.
- 5. 다시 재귀 호출한다. 뎁스는 2
- 6. 방문하지 않은 곳을 방문한다. List<Integer> 에 적재한다.
- 7. 다시 재귀 호출한다. List<Integer>의 사이즈를 체크한다. size가 nums.length와 같아지면 return 한다.
시간복잡도
- O(n!)
*/
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        backtracking(visited, answer, new ArrayList<>(), nums);
        return answer;
    }

    private void backtracking(boolean[]visited, List<List<Integer>> answer, List<Integer> li, int[] nums) {
        if (li.size() == nums.length) {
            answer.add(new ArrayList<>(li));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                li.add(nums[i]);
                backtracking(visited, answer, li, nums);
                li.remove(li.size() - 1);
                visited[i] = false;
            }
        } 
    }
}