#
# @lc app=leetcode id=200 lang=python3
#
# [200] Number of Islands
#
# https://leetcode.com/problems/number-of-islands/description/
#
# algorithms
# Medium (41.47%)
# Likes:    2616
# Dislikes: 94
# Total Accepted:    359.3K
# Total Submissions: 863.2K
# Testcase Example:  '[["1","1","1","1","0"],["1","1","0","1","0"],["1","1","0","0","0"],["0","0","0","0","0"]]'
#
# Given a 2d grid map of '1's (land) and '0's (water), count the number of
# islands. An island is surrounded by water and is formed by connecting
# adjacent lands horizontally or vertically. You may assume all four edges of
# the grid are all surrounded by water.
#
# Example 1:
#
#
# Input:
# 11110
# 11010
# 11000
# 00000
#
# Output: 1
#
#
# Example 2:
#
#
# Input:
# 11000
# 11000
# 00100
# 00011
#
# Output: 3
#
#

# @lc code=start
class Solution:
    def numIslands(self, grid: List[List[str]]) -> int:
        result = 0
        for x in range(len(grid)):
            for y in range(len(grid[0])):
                if grid[x][y] == "0":
                    continue
                result += 1
                self.bfs(grid, x, y)
        return result
    def bfs(self, grid, x, y):
        if (x >= len(grid) or x < 0 or y >= len(grid[0]) or y < 0):
            return
        if grid[x][y] == "0":
            return
        grid[x][y] = "0"
        self.bfs(grid, x + 1, y)
        self.bfs(grid, x - 1, y)
        self.bfs(grid, x, y + 1)
        self.bfs(grid, x, y - 1)


# @lc code=end

