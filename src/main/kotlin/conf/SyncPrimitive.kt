package org.example.conf

import kotlinx.coroutines.*

private var count: Int = 0

private fun increment() {
    count++
}

fun syncPrimitiveExample() = runBlocking {
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

fun main() = syncPrimitiveExample()

// Варианты ответов:
// 1_000_000
// 0 - 1_000_000
// Exception
// not compile

// нельзя синхронизироваться на примитивах, нужно обернуть в data class Counter