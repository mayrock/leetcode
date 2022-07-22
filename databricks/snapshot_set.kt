interface SnapshotSet<T> {
    fun add(value: T)
    fun remove(value: T)
    fun contains(value: T): Boolean
    fun iterator(): Iterator<T>
}

class SnapshotSetImpl<T>: SnapshotSet<T> {
    data class Version(public val addedVersion: Int) {
        public var removedVersion: Int = Int.MAX_VALUE
        public fun isDeleted() = removedVersion == Int.MAX_VALUE
        public fun delete(v: Int) { removedVersion = v }
    }

    class VersionHistory<T>(val value: T, addedVersion: Int) {
        val list: MutableList<Version>
        init {
            list = mutableListOf(Version(addedVersion))
        }

        fun isPresent(v: Int): Boolean {
            for (i in list.indices.reversed()) {
                if (list[i].addedVersion <= v) {
                    return list[i].removedVersion > v
                }
            }

            return false
        }

        fun add(v: Int) {
            // TODO sanity check
            if (!isPresent(v)) {
                list.add(Version(v))
            }
        }

        fun delete(v: Int) {
            // presence call is done by caller
            // TODO sanity check
            list[list.lastIndex].delete(v)
        }
    }

    public val internalSet  = mutableSetOf<T>()
    public val history  = mutableListOf<VersionHistory<T>>()
    public val valueToHistoryIndexMap  = mutableMapOf<T, Int>()
    var currentVersion = 0

    override fun add(value: T) { 
        internalSet.add(value)
        var index = valueToHistoryIndexMap[value]
        if (index == null) {
            history.add(VersionHistory(value, currentVersion))
            valueToHistoryIndexMap[value] = history.size - 1 
        } else {
            history[index].add(currentVersion)
        }
    }

    override fun remove(value: T) { 
        if (!contains(value)) {
            throw RuntimeException("Entry does not exist")
        }
        internalSet.remove(value)
        history[valueToHistoryIndexMap[value]!!].delete(currentVersion)
    }

    override fun contains(value: T): Boolean {
        return internalSet.contains(value)
    }

    override fun iterator(): Iterator<T> { 
        val ret = SnapshotSetIterator(this, currentVersion) 
        currentVersion++
        return ret
    }
    
    class SnapshotSetIterator<T>(val set: SnapshotSetImpl<T>, val version: Int): Iterator<T> {
        var currentIndex = -1
        private fun findNextIndex(): Int? {
            return ((currentIndex + 1)..(set.history.size - 1)).firstOrNull { set.history[it].isPresent(version) }
        }

        override fun hasNext(): Boolean {
            return findNextIndex() != null
        }

        override fun next(): T {
            currentIndex = findNextIndex()!!
            return set.history[currentIndex].value
        }
        
    }
}

fun main() {
    val set = SnapshotSetImpl<Int>()
    set.add(1)
    set.add(5)
    set.add(8)
    val iter = set.iterator()
    set.remove(5)
    val iter2 = set.iterator()
    set.add(5)
    set.add(10)
    for (i in iter) { print("$i ") }
    println()
    for (i in iter2) { print("$i ") }
    println()
    for (i in set.iterator()) { print("$i ") }
    println()
    println(set.history.map { it.list })
    println(set.valueToHistoryIndexMap)
}