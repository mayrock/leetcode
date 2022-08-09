/*
 * @lc app=leetcode id=890 lang=kotlin
 *
 * [890] Find and Replace Pattern
 *
 * https://leetcode.com/problems/find-and-replace-pattern/description/
 *
 * algorithms
 * Medium (78.02%)
 * Likes:    3290
 * Dislikes: 152
 * Total Accepted:    156.2K
 * Total Submissions: 200.2K
 * Testcase Example:  '["abc","deq","mee","aqq","dkd","ccc"]\n"abb"'
 *
 * Given a list of strings words and a string pattern, return a list of
 * words[i] that match pattern. You may return the answer in any order.
 * 
 * A word matches the pattern if there exists a permutation of letters p so
 * that after replacing every letter x in the pattern with p(x), we get the
 * desired word.
 * 
 * Recall that a permutation of letters is a bijection from letters to letters:
 * every letter maps to another letter, and no two letters map to the same
 * letter.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
 * Output: ["mee","aqq"]
 * Explanation: "mee" matches the pattern because there is a permutation {a ->
 * m, b -> e, ...}. 
 * "ccc" does not match the pattern because {a -> c, b -> c, ...} is not a
 * permutation, since a and b map to the same letter.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: words = ["a","b","c"], pattern = "a"
 * Output: ["a","b","c"]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= pattern.length <= 20
 * 1 <= words.length <= 50
 * words[i].length == pattern.length
 * pattern and words[i] are lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    fun findAndReplacePattern(words: Array<String>, pattern: String): List<String> {
        val encodedPattern = encode(pattern) 
        return words.filter { encode(it) == encodedPattern }
    }

    private fun encode(s: String): List<Int> {
        var charToIndexMap = mutableMapOf<Char, Int>()
        var resultList = mutableListOf<Int>()
        for (char in s) {
            if (charToIndexMap.containsKey(char)) {
                val charIndex = charToIndexMap[char]!!
                resultList[charIndex] = resultList[charIndex] + 1
            } else {
                val charIndex = charToIndexMap.size
                charToIndexMap[char] = charIndex
                resultList.add(1)
            }
        }

        return resultList
    }
}
// @lc code=end

