#
# @lc app=leetcode id=45 lang=python3
#
# [45] Jump Game II
#
# https://leetcode.com/problems/jump-game-ii/description/
#
# algorithms
# Hard (29.93%)
# Likes:    2095
# Dislikes: 119
# Total Accepted:    236.7K
# Total Submissions: 790.4K
# Testcase Example:  '[2,3,1,1,4]'
#
# Given an array of non-negative integers, you are initially positioned at the
# first index of the array.
#
# Each element in the array represents your maximum jump length at that
# position.
#
# Your goal is to reach the last index in the minimum number of jumps.
#
# Example:
#
#
# Input: [2,3,1,1,4]
# Output: 2
# Explanation: The minimum number of jumps to reach the last index is 2.
# ⁠   Jump 1 step from index 0 to 1, then 3 steps to the last index.
#
# Note:
#
# You can assume that you can always reach the last index.
#
#
from typing import List
# @lc code=start
class Solution:
    def jump(self, nums: List[int]) -> int:
        if len(nums) <= 1:
            return 0
        remainingLowerBound = 0
        q =  [(0, 0)]
        while q:
            pair = q.pop(0)
            if pair[0] == len(nums) - 1:
                return pair[1]
            start = max(pair[0] + 1, remainingLowerBound)
            for index in range(start, pair[0] + nums[pair[0]] + 1):
                if index == len(nums) - 1:
                    return pair[1] + 1
                q.append((index, pair[1] + 1))
                remainingLowerBound = index + 1

# @lc code=end
Solution().jump([2,3,1,1,4])

