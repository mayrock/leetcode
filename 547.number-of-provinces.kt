

import sun.java2d.xr.MutableInteger/*
 * @lc app=leetcode id=547 lang=kotlin
 *
 * [547] Number of Provinces
 *
 * https://leetcode.com/problems/number-of-provinces/description/
 *
 * algorithms
 * Medium (62.86%)
 * Likes:    5650
 * Dislikes: 248
 * Total Accepted:    501.7K
 * Total Submissions: 798.1K
 * Testcase Example:  '[[1,1,0],[1,1,0],[0,0,1]]'
 *
 * There are n cities. Some of them are connected, while some are not. If city
 * a is connected directly with city b, and city b is connected directly with
 * city c, then city a is connected indirectly with city c.
 * 
 * A province is a group of directly or indirectly connected cities and no
 * other cities outside of the group.
 * 
 * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the
 * i^th city and the j^th city are directly connected, and isConnected[i][j] =
 * 0 otherwise.
 * 
 * Return the total number of provinces.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * Output: 2
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * Output: 3
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 200
 * n == isConnected.length
 * n == isConnected[i].length
 * isConnected[i][j] is 1 or 0.
 * isConnected[i][i] == 1
 * isConnected[i][j] == isConnected[j][i]
 * 
 * 
 */

// @lc code=start
class Solution {
    fun findCircleNum(isConnected: Array<IntArray>): Int {
        val visited = mutableSetOf<Int>()
        var numProvinces = 0
        for (i in 0 until isConnected.size) {
            if (visited.contains(i)) {
                continue
            }

            numProvinces++
            bfsVisit(isConnected, i, visited)
        }

        return numProvinces

    }

    fun bfsVisit(isConnected: Array<IntArray>, point: Int, visited: MutableSet<Int>) {
        if (visited.contains(point)) {
            return
        }

        visited.add(point)
        for (i in 0 until isConnected.size) {
            if (isConnected[point][i] == 1) {
                bfsVisit(isConnected, i, visited)
            }
        }
    }
}
// @lc code=end

