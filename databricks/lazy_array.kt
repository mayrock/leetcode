interface LazyArray<T> {
    fun <TOut> map(mapper: (T) -> TOut): LazyArray<TOut>
    fun filter(func: (T) -> Boolean): LazyArray<T>
    fun indexOf(v: T): Int
}

abstract class LazyArrayInternal<T>: LazyArray<T> {
    abstract fun execute(): List<T>
    override fun indexOf(v: T): Int {
        return execute().indexOf(v)
    }

    override fun <TOut> map(mapper: (T) -> TOut): LazyArray<TOut> {
        return LazyArrayMapped(this, mapper)
    }

    override fun filter(func: (T) -> Boolean): LazyArray<T> {
        return LazyArrayFiltered(this, func)
    }
}


class LazyArrayWithValues<T>(public val values: List<T>): LazyArrayInternal<T>() {
    override fun indexOf(v: T): Int {
        return values.indexOf(v)
    }
    
    override fun execute(): List<T> = values
}

class LazyArrayMapped<T, TOut>(val inner: LazyArrayInternal<T>, val mapper: (T) -> TOut): LazyArrayInternal<TOut>() {
    override fun execute(): List<TOut> { return inner.execute().map(mapper) }
}

class LazyArrayFiltered<T>(val inner: LazyArrayInternal<T>, val filterFunc: (T) -> Boolean): LazyArrayInternal<T>() {
    override fun execute(): List<T> { return inner.execute().filter(filterFunc) }
}

fun main() {
    val list = listOf(1,2,3,4,5);
    val lazyList = LazyArrayWithValues(list)
    val filtered = lazyList.filter { 
        println("filtering $it")
        it > 3.0
    }
    println("after calling filter")
    val mapped = filtered.map { 
        println("mapping $it")
        it * 1.0 
    }
    println("after calling map")
    val mapped2 = filtered.map { 
        println("should not map $it")
        it * 1.0 
    }
    println(mapped.indexOf(4.0))
}
