/*
 * @lc app=leetcode id=1147 lang=kotlin
 *
 * [1147] Longest Chunked Palindrome Decomposition
 *
 * https://leetcode.com/problems/longest-chunked-palindrome-decomposition/description/
 *
 * algorithms
 * Hard (59.99%)
 * Likes:    469
 * Dislikes: 24
 * Total Accepted:    17.4K
 * Total Submissions: 29.1K
 * Testcase Example:  '"ghiabcdefhelloadamhelloabcdefghi"'
 *
 * You are given a string text. You should split it to k substrings (subtext1,
 * subtext2, ..., subtextk) such that:
 * 
 * 
 * subtexti is a non-empty string.
 * The concatenation of all the substrings is equal to text (i.e., subtext1 +
 * subtext2 + ... + subtextk == text).
 * subtexti == subtextk - i + 1 for all valid values of i (i.e., 1 <= i <=
 * k).
 * 
 * 
 * Return the largest possible value of k.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: text = "ghiabcdefhelloadamhelloabcdefghi"
 * Output: 7
 * Explanation: We can split the string on
 * "(ghi)(abcdef)(hello)(adam)(hello)(abcdef)(ghi)".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: text = "merchant"
 * Output: 1
 * Explanation: We can split the string on "(merchant)".
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: text = "antaprezatepzapreanta"
 * Output: 11
 * Explanation: We can split the string on
 * "(a)(nt)(a)(pre)(za)(tep)(za)(pre)(a)(nt)(a)".
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= text.length <= 1000
 * text consists only of lowercase English characters.
 * 
 * 
 */

// @lc code=start
import kotlin.text.*

class Solution {
    fun longestDecomposition(text: String): Int {
        var result = 0
        var i = 0
        while (i <= text.length / 2) {
            var length = 1
            var earlyBreak = false
            while (length <= text.length / 2 - i) {
                val str1 = text.substring(i, i + length)
                val str2 = text.substring(text.length - i - length, text.length - i)
                println("i is $i, length is $length, comparing $str1 with $str2")
                if (str1 == str2) {
                    result += 2
                    i = i + length
                    earlyBreak = true
                    break
                } else {
                    length++
                }
            }
            if (!earlyBreak) {
                if (i * 2 != text.length) result++
                break
            }

        }
        return result
    }
}
// @lc code=end

fun main() {
    val s = Solution()
    println(s.longestDecomposition("elvtoelvto"))
}

