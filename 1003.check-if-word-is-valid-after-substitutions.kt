/*
 * @lc app=leetcode id=1003 lang=kotlin
 *
 * [1003] Check If Word Is Valid After Substitutions
 *
 * https://leetcode.com/problems/check-if-word-is-valid-after-substitutions/description/
 *
 * algorithms
 * Medium (58.04%)
 * Likes:    666
 * Dislikes: 446
 * Total Accepted:    43.8K
 * Total Submissions: 75.5K
 * Testcase Example:  '"aabcbc"'
 *
 * Given a string s, determine if it is valid.
 * 
 * A string s is valid if, starting with an empty string t = "", you can
 * transform t into s after performing the following operation any number of
 * times:
 * 
 * 
 * Insert string "abc" into any position in t. More formally, t becomes tleft +
 * "abc" + tright, where t == tleft + tright. Note that tleft and tright may be
 * empty.
 * 
 * 
 * Return true if s is a valid string, otherwise, return false.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "aabcbc"
 * Output: true
 * Explanation:
 * "" -> "abc" -> "aabcbc"
 * Thus, "aabcbc" is valid.
 * 
 * Example 2:
 * 
 * 
 * Input: s = "abcabcababcc"
 * Output: true
 * Explanation:
 * "" -> "abc" -> "abcabc" -> "abcabcabc" -> "abcabcababcc"
 * Thus, "abcabcababcc" is valid.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "abccba"
 * Output: false
 * Explanation: It is impossible to get "abccba" using the operation.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 2 * 10^4
 * s consists of letters 'a', 'b', and 'c'
 * 
 * 
 */

// @lc code=start
class Solution {
    fun isValid(s: String): Boolean {
        var oldS = s
        while (true) {
            val newS = oldS.replace("abc","")
            if (newS == "") {
                return true
            }
            if (newS == oldS) {
                return false
            }
            oldS = newS
        }
    }
}
// @lc code=end

