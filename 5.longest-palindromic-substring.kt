/*
 * @lc app=leetcode id=5 lang=kotlin
 *
 * [5] Longest Palindromic Substring
 *
 * https://leetcode.com/problems/longest-palindromic-substring/description/
 *
 * algorithms
 * Medium (32.25%)
 * Likes:    20135
 * Dislikes: 1159
 * Total Accepted:    2M
 * Total Submissions: 6.2M
 * Testcase Example:  '"babad"'
 *
 * Given a string s, return the longest palindromic substring in s.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "babad"
 * Output: "bab"
 * Explanation: "aba" is also a valid answer.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "cbbd"
 * Output: "bb"
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 1000
 * s consist of only digits and English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    fun longestPalindrome(s: String): String {
        var result = ""
        val rev = s.reversed()
        for (i in 0 until s.length) {
            for (j in i + 1 until s.length + 1) {
                val substr = s.substring(i, j)
                if (substr == rev.substring(s.length - j, s.length - i)) {
                    if (substr.length > result.length) {
                        result = substr
                    }
                }
            }
        }
        return result
    }
}
// @lc code=end

