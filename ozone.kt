import java.io.*;
import java.util.*;

/*
You are given an array of characters containing only 'X's and 'O's, e.g. 'OXOOX'.

Define an "O-range" as an ordered pair [i, j) such that all characters from i (inclusive) to j (exclusive) are 'O's.

Return the number of unique, non-empty O-ranges in the array.

Example: 'OXOOX'
Output: 4

Explanation:
01234
OXOOX
_     [0,1)
  _   [2,3)
  __  [2,4)
   _  [3,4)
*/

/*

n -> f(n)
1 -> 1
2 -> 3
3 -> 6
4 -> 10
n -> n + (n-1) + (n-3) .. + 1 = n*(n+1) / 2

*/


fun main0() {
  println(countRange("OXOOXXOOOXXX"))
  println(countRange("XOOXXOOOXXXO"))
  println(countRange(""))
  println(countRange("O"))
  println(countRange("X"))
}

fun countRange(input: String): Int {
  var currentOLength = 0
  var result = 0
  for (c in input) {
    if (c == 'O') {
      currentOLength++
    } else {
      if (currentOLength > 0) {
        result += currentOLength * (currentOLength + 1) / 2
        currentOLength = 0
      }
    }
  }
  if (currentOLength > 0) {
      result += currentOLength * (currentOLength + 1) / 2
      currentOLength = 0
    }
  return result
}

/*
You are given a 2D array of characters containing only 'X's and 'O's. 

Define an "O-rectangle" as a pair of points ((top, left),(bottom,right)) such that all characters from (top, left) inclusive to (bottom, right) exclusive are 'O's.

Return the number of unique O-rectangles in the array.

Example:

Input:
OXO
XOO

Output:
6

Explanation:
Adding indexes for reference:
  0 1 2 3
0 O X O O
1 X O O X
2 O O O O
3 O O O X
4 O O X X

The O-rectangles are
- singles:  ((0,0),(1,1)), ((0,2),(1,3)), ((1,1),(2,2)), ((1,2),(2,3))
- horizontal: ((1,1),(2,3))
- vertical:   ((0,2),(2,3))

n * m -> n * m
*/

fun main() {
  val input = listOf(
    listOf('O', 'O', 'O'),
    listOf('O', 'O', 'X'),
    listOf('O', 'X', 'O')

  )
  println(countRectangle(input))
}

fun countRectangle(array: List<List<Char>>): Int {
  var result = 0
  for (i in 0 until array.size) {
    for (j in 0 until array[0].size) {
      result += countRectangle(array, i, j)
    }
  }
  return result
}

fun countRectangle(array: List<List<Char>>, x: Int, y: Int): Int {
  val mem = MutableList<MutableList<Boolean>>(array.size - x) { MutableList<Boolean>(array[0].size - y) { false } }
  var total = 0
  var maxY = array[0].size - y
  for (i in 0 until array.size - x) {
    if (maxY <= 0) {
      break
    }
    for (j in 0 until maxY) {
      val ifPointIsO = array[x + i][y + j] == 'O'
      if (!ifPointIsO) {
        maxY = j
        break
      }
      val leftIsTrue = i == 0 || mem[i - 1][j] == true
      val topIsTrue = j == 0 || mem[i][j - 1] == true
      val thisIsTrue = ifPointIsO && leftIsTrue && topIsTrue
      mem[i][j] = thisIsTrue
      if (thisIsTrue) {
        total++
      }
    }
  }
  return total

}

