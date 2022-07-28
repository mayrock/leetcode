

import kotlin.math.absoluteValue/*
 * @lc app=leetcode id=220 lang=kotlin
 *
 * [220] Contains Duplicate III
 *
 * https://leetcode.com/problems/contains-duplicate-iii/description/
 *
 * algorithms
 * Medium (21.71%)
 * Likes:    2516
 * Dislikes: 2380
 * Total Accepted:    209.5K
 * Total Submissions: 965.1K
 * Testcase Example:  '[1,2,3,1]\n3\n0'
 *
 * Given an integer array nums and two integers k and t, return true if there
 * are two distinct indices i and j in the array such that abs(nums[i] -
 * nums[j]) <= t and abs(i - j) <= k.
 * 
 * 
 * Example 1:
 * Input: nums = [1,2,3,1], k = 3, t = 0
 * Output: true
 * Example 2:
 * Input: nums = [1,0,1,1], k = 1, t = 2
 * Output: true
 * Example 3:
 * Input: nums = [1,5,9,1,5,9], k = 2, t = 3
 * Output: false
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 2 * 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 * 0 <= k <= 10^4
 * 0 <= t <= 2^31 - 1
 * 
 * 
 */

// @lc code=start
import kotlin.math.*

class Solution {
    fun containsNearbyAlmostDuplicate(nums: IntArray, k: Int, t: Int): Boolean {
        for (i in 0 until nums.size) {
            for (j in (i + 1) until min(i + k + 1, nums.size)) {
                if ((nums[i] - nums[j]).absoluteValue <= t) {
                    return true
                }
            }
        }

        return false
    }
}
// @lc code=end

