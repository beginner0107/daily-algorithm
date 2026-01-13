// Problem: 1. Two Sum (Easy)
// Link: https://leetcode.com/problems/two-sum

class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        map = {}
        for index, num in enumerate(nums):
            t = target - num
            if t in map:
                return [map.get(t), index]
            map[num] = index
        