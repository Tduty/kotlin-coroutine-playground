package org.example.playground.race

import kotlinx.coroutines.*
import org.example.Counter
import org.example.passed
import java.time.Instant.now

private val counter = Counter()

private fun increment() {
    counter.count++
}

fun main() = runBlocking {
    val time = now()

    val jobs = List(1_000) {
        launch(Dispatchers.Default) {
            synchronized(counter) {
                repeat(1_000) {
                    increment()
                }
            }
        }
    }
    jobs.joinAll()

    println("Final count: ${counter.count} in ${time.passed} seconds")
}