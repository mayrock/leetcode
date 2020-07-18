#
# @lc app=leetcode id=32 lang=python3
#
# [32] Longest Valid Parentheses
#
# https://leetcode.com/problems/longest-valid-parentheses/description/
#
# algorithms
# Hard (27.61%)
# Likes:    3153
# Dislikes: 130
# Total Accepted:    270.2K
# Total Submissions: 971.9K
# Testcase Example:  '"(()"'
#
# Given a string containing just the characters '(' and ')', find the length of
# the longest valid (well-formed) parentheses substring.
#
# Example 1:
#
#
# Input: "(()"
# Output: 2
# Explanation: The longest valid parentheses substring is "()"
#
#
# Example 2:
#
#
# Input: ")()())"
# Output: 4
# Explanation: The longest valid parentheses substring is "()()"
#
#
#

# @lc code=start
class Solution:
    def longestValidParentheses(self, s: str) -> int:
        leftCount, result, maxResult = 0, 0, 0
        for c in s:
            if c == '(':
               leftCount += 1
            else:
                if leftCount >= 1:
                    leftCount -= 1
                    result += 2
                    if result > maxResult:
                        maxResult = result
                else:
                    result = 0
        return maxResult
# @lc code=end

