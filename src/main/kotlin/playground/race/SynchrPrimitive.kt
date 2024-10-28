@file:Suppress("FORBIDDEN_SYNCHRONIZED_BY_VALUE_CLASSES_OR_PRIMITIVES")

package org.example.playground.race

import kotlinx.coroutines.*

private var count: Int = 0

private fun increment() {
    count++
}

fun main() = runBlocking {
    val jobs = List(1_000) {
        launch(Dispatchers.Default) {
            synchronized(count) {
                repeat(1_000) {
                    increment()
                }
            }
        }
    }
    jobs.joinAll()

    println("Final count: $count")
}

// 1_000_000
// 0 - 1_000_000
// Exception
// not compile