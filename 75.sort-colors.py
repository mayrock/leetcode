#
# @lc app=leetcode id=75 lang=python3
#
# [75] Sort Colors
#
class Solution:
    def sortColors(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        lastZeroIndex = -1
        firstUnseenIndex = 0
        lastUnseenIndex = len(nums) - 1

        while (firstUnseenIndex <= lastUnseenIndex):
            if (nums[firstUnseenIndex] == 1):
                firstUnseenIndex += 1
            elif (nums[firstUnseenIndex] == 2):
                nums[firstUnseenIndex] = nums[lastUnseenIndex]
                nums[lastUnseenIndex] = 2
                lastUnseenIndex -= 1
            else:
                nums[firstUnseenIndex] = nums[lastZeroIndex + 1]
                firstUnseenIndex += 1
                nums[lastZeroIndex + 1] = 0
                lastZeroIndex += 1







