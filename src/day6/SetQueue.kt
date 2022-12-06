package day6

class SetQueue<T>(private val size: Int) {
    private val hashSet = HashSet<T>()
    private val queue = ArrayDeque<T>()

    fun add(value: T) {
        if (hashSet.contains(value)) {
            while (!queue.isEmpty() && queue.first() != value) {
                val popped = queue.removeFirst()
                hashSet.remove(popped)
            }
            queue.removeFirst()
        }

        hashSet.add(value)
        queue.add(value)
    }

    fun isFull(): Boolean {
        return queue.size == size
    }
}
