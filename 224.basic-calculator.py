#
# @lc app=leetcode id=224 lang=python3
#
# [224] Basic Calculator
#
# https://leetcode.com/problems/basic-calculator/description/
#
# algorithms
# Hard (32.64%)
# Likes:    735
# Dislikes: 90
# Total Accepted:    108.8K
# Total Submissions: 331K
# Testcase Example:  '"1 + 1"'
#
# Implement a basic calculator to evaluate a simple expression string.
#
# The expression string may contain open ( and closing parentheses ), the plus
# + or minus sign -, non-negative integers and empty spaces  .
#
# Example 1:
#
#
# Input: "1 + 1"
# Output: 2
#
#
# Example 2:
#
#
# Input: " 2-1 + 2 "
# Output: 3
#
# Example 3:
#
#
# Input: "(1+(4+5+2)-3)+(6+8)"
# Output: 23
# Note:
#
#
# You may assume that the given expression is always valid.
# Do not use the eval built-in library function.
#
#
#

# @lc code=start
class Solution:
    def calculate(self, s: str) -> int:
        #print("a" + s)
        s = s.replace(" ", "")
        if s.count(")") == 0:
            return self.calculateNoParentheses(s)
        rightIndex = s.index(")")
        leftIndex = s.rindex("(", 0, rightIndex)
        result = self.calculateNoParentheses(s[leftIndex + 1:rightIndex])
        newStr = s[:leftIndex] + str(result) + s[rightIndex+1:]
        return self.calculate(newStr)

    def calculateNoParentheses(self, s: str) -> int:
        #print("b" + s)
        processed = s.replace("+-", "-").replace("--","+").replace("-", "+-")
        strArr = processed.split("+")
        arr = [int(e) for e in strArr if e]
        return sum(arr)

# @lc code=end

ret = Solution().calculate("(5-(1+(5)))")
