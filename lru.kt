import java.io.*;
import java.util.*;


/*
Design a data structure for a Least Recently Used (LRU) cache.
Implement the LRUCache class:
LRUCache(int capacity):
int get(int key): Return -1 if not found;
void put(int key, int value):  Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.


Capacity = 2
put(1, 100)
put(2, 200)
get(1)
put(3, 300)
get(2)
put(4, 400)
get(1)
get(3)
get(4)

*/

fun main() {
  val cache = LruCache(2)
  println(cache.get(1))
  cache.put(1, 100)
  cache.put(2, 200)
  println(cache.get(1))
  cache.put(3, 300)
  println(cache.get(2))
  cache.put(4, 400)
  println(cache.get(1))
  println(cache.get(3))
  println(cache.get(4))
}

class CacheEntry(val key: Int, var value: Int, var pre: CacheEntry? = null, var next: CacheEntry? = null) {
}

class LruCache(val capacity: Int) {
  var head: CacheEntry? = null
  var tail: CacheEntry? = null
  val map = mutableMapOf<Int, CacheEntry>()

  fun get(key: Int): Int {
    val entry = map[key]
    entry?.let { moveToHead(it) }
    return entry?.value ?: -1
  }

  fun put(key: Int, value: Int) {
    if (!map.containsKey(key)) {
      if (map.size == capacity) {
        map.remove(tail!!.key)
        tail!!.pre?.let { it.next = null }
        tail = tail!!.pre
      }
      val newNode = CacheEntry(key, value)
      moveToHead(newNode)
      if (map.isEmpty()) {
        tail = newNode
      }

      map[key] = newNode
    }

    val entry = map[key]!!
    entry.value = value
    moveToHead(entry)
  }

  private fun moveToHead(node: CacheEntry) {
    if (node == head) {
      return
    }

    if (node == tail) {
      tail = node.pre
    }

    node.pre?.let { it.next = node.next }
    node.next?.let { it.pre = node.pre }
    node.next = head
    head?. let { it.pre = node }
    head = node
  }
}











/*
 * 
There are a total of n courses you have to take, labeled from 0 to n-1. You are given an array of prerequisites p where p[i] = [ai, bi] which indicates that in order to take ai, you must take course bi first.
For example, the pair [0, 1], indicates that in order to take course 0, you have to first take course 1.
Return the ordering of courses you should take to finish all courses. If there are multiple valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
 int[][] input1 = {{1,0},{2,0},{3,1},{3,2}};
– [0, 1, 2, 3]
    int[][] input2= {{3,2},{1,3},{0,4},{1,4},{0,5},{5,2}};
– [2, 4, 3, 5, 1, 0]

 */

// To execute Kotlin code, please define a top level function named main

class Node(val courseId: Int, val dependees: MutableSet<Node> = mutableSetOf(), val dependencies: MutableSet<Node> = mutableSetOf() ) {
}

fun main2() {
  var n = 4
  var links = listOf(Pair(1,0),Pair(2,0),Pair(3,1),Pair(3,2))
  println(courseOrdering(n, links))

  n = 6
  links = listOf(Pair(3,2), Pair(1,3), Pair(0,4), Pair(1,4), Pair(0,5), Pair(5,2))
  println(courseOrdering(n, links))

  n = 4
  links = listOf(Pair(1,0),Pair(2,0),Pair(1,2),Pair(2,3), Pair(3,1))
  println(courseOrdering(n, links))
}

fun courseOrdering(n: Int, links: List<Pair<Int, Int>>): List<Int> {
  val leafNodes = mutableSetOf<Node>()
  val allNodes = (0 until n).map { Pair(it, Node(it)) }.toMap()

  for (link in links) {
    val dependee = allNodes[link.first]!!
    val dependency = allNodes[link.second]!!
    dependee.dependencies.add(dependency)
    dependency.dependees.add(dependee)
  }

  for (node in allNodes.values) {
    if (node.dependencies.isEmpty()) {
      leafNodes.add(node)
    }
  }

  val resultList = mutableListOf<Int>()
  while (!leafNodes.isEmpty()) {
    val leaf = leafNodes.first()
    resultList.add(leaf.courseId)
    leafNodes.remove(leaf)
    leaf.dependees.forEach {
      it.dependencies.remove(leaf)
      if (it.dependencies.isEmpty()) {
        leafNodes.add(it)
      }
    }
  }

  return if (resultList.size == n) resultList else listOf()
}
