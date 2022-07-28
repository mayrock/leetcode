/*
 * @lc app=leetcode id=41 lang=kotlin
 *
 * [41] First Missing Positive
 *
 * https://leetcode.com/problems/first-missing-positive/description/
 *
 * algorithms
 * Hard (36.24%)
 * Likes:    10422
 * Dislikes: 1391
 * Total Accepted:    715.8K
 * Total Submissions: 2M
 * Testcase Example:  '[1,2,0]'
 *
 * Given an unsorted integer array nums, return the smallest missing positive
 * integer.
 * 
 * You must implement an algorithm that runs in O(n) time and uses constant
 * extra space.
 * 
 * 
 * Example 1:
 * Input: nums = [1,2,0]
 * Output: 3
 * Example 2:
 * Input: nums = [3,4,-1,1]
 * Output: 2
 * Example 3:
 * Input: nums = [7,8,9,11,12]
 * Output: 1
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 5 * 10^5
 * -2^31 <= nums[i] <= 2^31 - 1
 * 
 * 
 */

// @lc code=start
class Solution {
    fun firstMissingPositive(nums: IntArray): Int {
        for (i in nums.indices) {
            var index = i
            var value = nums[index]
            while (value != index + 1 && value > 0 && value <= nums.size) {
                index = nums[index] - 1
                value = nums[index]
                nums[index] = index + 1
            }
        }
        return nums.withIndex().firstOrNull { it.index + 1 != it.value }?.index?.plus(1) ?: nums.size + 1
    }
}
// @lc code=end

fun main() {
    val s = Solution()
    println(s.firstMissingPositive(intArrayOf(1,2,3,5,7,4)))
}

