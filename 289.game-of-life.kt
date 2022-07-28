/*
 * @lc app=leetcode id=289 lang=kotlin
 *
 * [289] Game of Life
 *
 * https://leetcode.com/problems/game-of-life/description/
 *
 * algorithms
 * Medium (65.97%)
 * Likes:    4957
 * Dislikes: 452
 * Total Accepted:    354.6K
 * Total Submissions: 537.5K
 * Testcase Example:  '[[0,1,0],[0,0,1],[1,1,1],[0,0,0]]'
 *
 * According to Wikipedia's article: "The Game of Life, also known simply as
 * Life, is a cellular automaton devised by the British mathematician John
 * Horton Conway in 1970."
 * 
 * The board is made up of an m x n grid of cells, where each cell has an
 * initial state: live (represented by a 1) or dead (represented by a 0). Each
 * cell interacts with its eight neighbors (horizontal, vertical, diagonal)
 * using the following four rules (taken from the above Wikipedia
 * article):
 * 
 * 
 * Any live cell with fewer than two live neighbors dies as if caused by
 * under-population.
 * Any live cell with two or three live neighbors lives on to the next
 * generation.
 * Any live cell with more than three live neighbors dies, as if by
 * over-population.
 * Any dead cell with exactly three live neighbors becomes a live cell, as if
 * by reproduction.
 * 
 * 
 * The next state is created by applying the above rules simultaneously to
 * every cell in the current state, where births and deaths occur
 * simultaneously. Given the current state of the m x n grid board, return the
 * next state.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
 * Output: [[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: board = [[1,1],[1,0]]
 * Output: [[1,1],[1,1]]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 25
 * board[i][j] is 0 or 1.
 * 
 * 
 * 
 * Follow up:
 * 
 * 
 * Could you solve it in-place? Remember that the board needs to be updated
 * simultaneously: You cannot update some cells first and then use their
 * updated values to update other cells.
 * In this question, we represent the board using a 2D array. In principle, the
 * board is infinite, which would cause problems when the active area
 * encroaches upon the border of the array (i.e., live cells reach the border).
 * How would you address these problems?
 * 
 * 
 */

// @lc code=start
class Solution {
    val DIRECTIONS = setOf(
        Pair(0, 1),
        Pair(0, -1),
        Pair(1, 0),
        Pair(-1, 0),
        Pair(1, 1),
        Pair(1, -1),
        Pair(-1, 1),
        Pair(-1, -1)
    )

    fun gameOfLife(board: Array<IntArray>): Unit {
        val newBoard = List(board.size) { MutableList(board[0].size) { 0 } }
        for (i in 0 until board.size) {
            for (j in 0 until board[i].size) {
                val c = countNeighbors(board, Pair(i, j))
                if ((board[i][j] == 1 && c >= 2 && c <= 3) || board[i][j] == 0 && c == 3) {
                    newBoard[i][j] = 1
                } else {
                    newBoard[i][j] = 0
                }
            }
        }

        for (i in 0 until board.size) {
            for (j in 0 until board[i].size) {
                board[i][j] = newBoard[i][j]
            }
        }
    }

    fun countNeighbors(board: Array<IntArray>, point: Pair<Int, Int>): Int {
        return DIRECTIONS
            .map { Pair(point.first + it.first, point.second + it.second) }
            .count { it.first >= 0 && it.first < board.size && it.second >= 0 && it.second < board[0].size && board[it.first][it.second] == 1 }
    }
}
// @lc code=end

