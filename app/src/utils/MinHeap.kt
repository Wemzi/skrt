package scr.utils

import java.util.Arrays

@SuppressWarnings("unchecked")
class MinHeap<T : Comparable<T>?>(private val initCapacity: Int) {
    private var heap: Array<T?>
    private var length: Int
    fun add(value: T) {
        if (length >= heap.size - 1) {
            heap = resize()
        }
        length++
        heap[length] = value
        bubbleUp()
    }

    fun rem(): T? {
        if (isEmpty) return null
        val result = peek()
        swap(1, length)
        heap[length] = null
        length--
        bubbleDown()
        return result
    }

    val isEmpty: Boolean
        get() = length == 0

    private fun peek(): T? {
        if (isEmpty) throw IllegalStateException()
        return heap[1]
    }

    private fun resize(): Array<T> {
        return Arrays.copyOf(heap, heap.size + initCapacity)
    }

    private fun bubbleUp() {
        var index = length
        while (hasParent(index) && parent(index)!!.compareTo(heap[index]) > 0) {
            swap(index, parentIndex(index))
            index = parentIndex(index)
        }
    }

    private fun bubbleDown() {
        var index = 1
        while (hasLeftChild(index)) {
            var smaller = leftIndex(index)
            if (hasRightChild(index) && heap[leftIndex(index)]!!.compareTo(heap[rightIndex(index)]) > 0) {
                smaller = rightIndex(index)
            }
            if (heap[index]!!.compareTo(heap[smaller]) > 0) {
                swap(index, smaller)
            } else break
            index = smaller
        }
    }

    private fun hasParent(i: Int): Boolean {
        return i > 1
    }

    private fun leftIndex(i: Int): Int {
        return i * 2
    }

    private fun rightIndex(i: Int): Int {
        return i * 2 + 1
    }

    private fun hasLeftChild(i: Int): Boolean {
        return leftIndex(i) <= length
    }

    private fun hasRightChild(i: Int): Boolean {
        return rightIndex(i) <= length
    }

    private fun parentIndex(i: Int): Int {
        return i / 2
    }

    private fun parent(i: Int): T? {
        return heap[parentIndex(i)]
    }

    private fun swap(index1: Int, index2: Int) {
        val temp = heap[index1]
        heap[index1] = heap[index2]
        heap[index2] = temp
    }

    @Override
    override fun toString(): String {
        var retval = ""
        for (each in heap) {
            if (each != null) retval += "$each : "
        }
        return """
            $retval
            
            """.trimIndent()
    }

    init {
        heap = arrayOfNulls<Comparable>(initCapacity) as Array<T?>
        length = 0
    }
}