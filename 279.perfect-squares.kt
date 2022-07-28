/*
 * @lc app=leetcode id=279 lang=kotlin
 *
 * [279] Perfect Squares
 */

// @lc code=start
import kotlin.math.*

class Solution {
    fun numSquares(n: Int): Int {
        // DP memory[i]: answer for i
        val memory = IntArray(n+1)
        memory[0] = 0
        memory[1] = 1
        for (i in 2..n) {
            val sqrt = sqrt(i.toDouble()).toInt()
            memory[i] = (1..sqrt).map { memory[i - it*it] }.min()!! + 1
        }
        return memory[n]
    }
}
// @lc code=end

