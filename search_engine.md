Invert index already exists



Example inverted index:
Cat =>
[1, 5, 6, 9, 10, 12, 16, 19, 22...]
Dog =>
[3, 5, 7, 9, 16, 17, 18, 19, 21...]
Elephant =>
[2, 6, 7, 9, 16, 19, 21, 25, 27...]
(Document ids lists in this inverted index are always sorted)

Given an input of search terms (eg: [Cat, Dog]), and an inverted index (like above), return all document ids that contain all of the search terms (eg: Cat, Dog => [5, 9, 16, 19...])



M: # terms
N: length of average list

Time: O(M*N)
Space: O(M*N)

N >> M
Print out results
O(M)

Class Pointer(val index: Int, val value: Int, val term: String)

Val valuesUnderPointer = PriorityQueue<Pointer>() { it.value }

// Initialize the priority queue
For (term in terms) {
	valuesUnderPointer.push(Pointer(0, map[term][0], term)
}

While (true) {
If (valuesUnderPointer.head().value == valuesUnderPointer.tail().value) {
	println(valuesUnderPointer.head().value)
}
	Val minPointer = valuesUnderPointer.pop()
	If (minPointer.index == map[minPointer.term].size) {
		break
}

Val newIndex = minPointer.index + 1
valuesUnderPointer.push(Pointer(newIndex, map[term][newIndex], term)
}


N => 10 billion
Max M => 100 thousands





The client sends search requests to our “Query Server”
The inverted index is sharded over multiple “Index Servers”

What sharding strategies can we use to distribute the inverted index over multiple Index 
Servers?

How might we compare these different sharding strategies?



Shard by term
Use term as key + consistent hashing
Traffic will be redirected to new servers?
Alternative: Keep partition table
Use term’s cluster 
Split a server in 2 when it reaches load.
Send requests to N index servers
Index server does phase 1 aggregation, query server does phase 2 aggregation
Optimization:
Query server gets 1st result from 1 index server, then send to 2nd index server, then do merge.
Add latency but reduce load
Some terms tend to coexist in queries
Use historical data to co-locate terms on the same machine
Minimize # servers to fan out
Imbalanced load if these servers are hot.
More replica?
Rough sizing: each term = 10bil 8bytes => 80gb
Can fit in 1 index server

Shard by doc id
Fan out to all servers
Index servers do aggregation, query server just needs to concat.
Pros:
Almost never have to split a server.  Just add new servers when we crawl more.
Query server logic is very simple. 
Reduce loads as well
Cons:
Fan out to all servers, scale up with # of index servers
We are likely to crawl related websites together, causing a server to contain a lot of hot documents.
Add replica
Or split.  But recommend replica since this is read-heavy.
RECOMMEND THIS:
Much less rebalancing work




Query Server:
Easy to scale up since its compute only
Optimizations:
Route traffic to the query server closest to the index servers.
Dispatch server layer / load balancing (thin) to calculate 
