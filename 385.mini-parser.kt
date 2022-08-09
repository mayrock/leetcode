/*
 * @lc app=leetcode id=385 lang=kotlin
 *
 * [385] Mini Parser
 *
 * https://leetcode.com/problems/mini-parser/description/
 *
 * algorithms
 * Medium (36.22%)
 * Likes:    382
 * Dislikes: 1184
 * Total Accepted:    49.2K
 * Total Submissions: 135.9K
 * Testcase Example:  '"324"'
 *
 * Given a string s represents the serialization of a nested list, implement a
 * parser to deserialize it and return the deserialized NestedInteger.
 * 
 * Each element is either an integer or a list whose elements may also be
 * integers or other lists.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "324"
 * Output: 324
 * Explanation: You should return a NestedInteger object which contains a
 * single integer 324.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "[123,[456,[789]]]"
 * Output: [123,[456,[789]]]
 * Explanation: Return a NestedInteger object containing a nested list with 2
 * elements:
 * 1. An integer containing value 123.
 * 2. A nested list containing two elements:
 * ⁠   i.  An integer containing value 456.
 * ⁠   ii. A nested list with one element:
 * ⁠        a. An integer containing value 789
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 5 * 10^4
 * s consists of digits, square brackets "[]", negative sign '-', and commas
 * ','.
 * s is the serialization of valid NestedInteger.
 * All the values in the input are in the range [-10^6, 10^6].
 * 
 * 
 */

// @lc code=start
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * class NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     constructor()
 *
 *     // Constructor initializes a single integer.
 *     constructor(value: Int)
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     fun isInteger(): Boolean
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     fun getInteger(): Int?
 *
 *     // Set this NestedInteger to hold a single integer.
 *     fun setInteger(value: Int): Unit
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     fun add(ni: NestedInteger): Unit
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     fun getList(): List<NestedInteger>?
 * }
 */
class Solution {
    fun deserialize(s: String): NestedInteger {
        val arr = s.split(",")
        return helper(arr, 0)
    }

    private fun helper(arr: List<String>, startIndex: Int): NestedInteger {
        val resultList = mutableListOf<Int>()
        var index = startIndex
        while ()
    }
}
// @lc code=end

