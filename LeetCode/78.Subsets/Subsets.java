// Problem: 78. Subsets (Medium)
// Link: https://leetcode.com/problems/subsets

class Solution {
    List<List<Integer>> li;
    int nm[];
    public List<List<Integer>> subsets(int[] nums) {
        li = new ArrayList<>();
        nm = nums;
        li.add(new ArrayList<>());
        recursive(0, new ArrayList<>());
        return li;
    }

    private void recursive(int index, List<Integer> l) {
        if (index >= nm.length) return;
        for (int i = index; i < nm.length; i++) {
            l.add(nm[i]);
            List<Integer> copyList = new ArrayList<>(l);
            li.add(copyList);
            recursive( i + 1, l);
            l.remove(l.size() - 1);
        }
    }
}