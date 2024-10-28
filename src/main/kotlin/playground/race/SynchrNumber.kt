package org.example.playground.race

import kotlinx.coroutines.*
import org.example.passed
import java.time.Instant.now

private var count: Number = 0

private fun increment() {
    count = count.toInt() + 1
}

fun main() = runBlocking {
    val time = now()
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

    println("Final count: $count in ${time.passed} seconds")
}