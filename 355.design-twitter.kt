/*
 * @lc app=leetcode id=355 lang=kotlin
 *
 * [355] Design Twitter
 *
 * https://leetcode.com/problems/design-twitter/description/
 *
 * algorithms
 * Medium (35.59%)
 * Likes:    2276
 * Dislikes: 302
 * Total Accepted:    95.2K
 * Total Submissions: 267.5K
 * Testcase Example:  '["Twitter","postTweet","getNewsFeed","follow","postTweet","getNewsFeed","unfollow","getNewsFeed"]\n' +
  '[[],[1,5],[1],[1,2],[2,6],[1],[1,2],[1]]'
 *
 * Design a simplified version of Twitter where users can post tweets,
 * follow/unfollow another user, and is able to see the 10 most recent tweets
 * in the user's news feed.
 * 
 * Implement the Twitter class:
 * 
 * 
 * Twitter() Initializes your twitter object.
 * void postTweet(int userId, int tweetId) Composes a new tweet with ID tweetId
 * by the user userId. Each call to this function will be made with a unique
 * tweetId.
 * List<Integer> getNewsFeed(int userId) Retrieves the 10 most recent tweet IDs
 * in the user's news feed. Each item in the news feed must be posted by users
 * who the user followed or by the user themself. Tweets must be ordered from
 * most recent to least recent.
 * void follow(int followerId, int followeeId) The user with ID followerId
 * started following the user with ID followeeId.
 * void unfollow(int followerId, int followeeId) The user with ID followerId
 * started unfollowing the user with ID followeeId.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input
 * ["Twitter", "postTweet", "getNewsFeed", "follow", "postTweet",
 * "getNewsFeed", "unfollow", "getNewsFeed"]
 * [[], [1, 5], [1], [1, 2], [2, 6], [1], [1, 2], [1]]
 * Output
 * [null, null, [5], null, null, [6, 5], null, [5]]
 * 
 * Explanation
 * Twitter twitter = new Twitter();
 * twitter.postTweet(1, 5); // User 1 posts a new tweet (id = 5).
 * twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1
 * tweet id -> [5]. return [5]
 * twitter.follow(1, 2);    // User 1 follows user 2.
 * twitter.postTweet(2, 6); // User 2 posts a new tweet (id = 6).
 * twitter.getNewsFeed(1);  // User 1's news feed should return a list with 2
 * tweet ids -> [6, 5]. Tweet id 6 should precede tweet id 5 because it is
 * posted after tweet id 5.
 * twitter.unfollow(1, 2);  // User 1 unfollows user 2.
 * twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1
 * tweet id -> [5], since user 1 is no longer following user 2.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= userId, followerId, followeeId <= 500
 * 0 <= tweetId <= 10^4
 * All the tweets have unique IDs.
 * At most 3 * 10^4 calls will be made to postTweet, getNewsFeed, follow, and
 * unfollow.
 * 
 * 
 */

// @lc code=start
class Twitter() {
    val followTracker = FollowTracker()

    val tweets = mutableListOf<Pair<Int, Int>>()

    fun postTweet(userId: Int, tweetId: Int) {
        tweets.add(Pair(userId, tweetId))
    }

    fun getNewsFeed(userId: Int): List<Int> {
        val results = mutableListOf<Int>()
        for (i in tweets.size - 1 downTo 0) {
            if (tweets[i].first == userId || followTracker.getFollowees(userId).contains(tweets[i].first)) {
                results.add(tweets[i].second)
                if (results.size >= 10) break
            }
        }
        return results
    }

    fun follow(followerId: Int, followeeId: Int) {
        followTracker.follow(followerId, followeeId)
    }

    fun unfollow(followerId: Int, followeeId: Int) {
        followTracker.unfollow(followerId, followeeId)
    }

}

class FollowTracker() {
    val map = mutableMapOf<Int, MutableSet<Int>>()

    fun follow(followerId: Int, followeeId: Int) {
        map.getOrPut(followerId) { mutableSetOf() }.add(followeeId)
    }

    fun unfollow(followerId: Int, followeeId: Int) {
        map[followerId]?.remove(followeeId)
    }

    fun getFollowees(followerId: Int): Set<Int> = map[followerId] ?: setOf()
}

/**
 * Your Twitter object will be instantiated and called as such:
 * var obj = Twitter()
 * obj.postTweet(userId,tweetId)
 * var param_2 = obj.getNewsFeed(userId)
 * obj.follow(followerId,followeeId)
 * obj.unfollow(followerId,followeeId)
 */
// @lc code=end

