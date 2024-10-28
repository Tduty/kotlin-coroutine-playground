package org.example.playground.race

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.example.passed
import java.time.Instant.now

private var count = 0

private fun increment() {
    count++
}

fun main() = runBlocking {
    val time = now()
    val mutex = Mutex()
    val jobs = List(1_000) {
        launch(Dispatchers.Default) {
            mutex.withLock {
                repeat(1_000) {
                    increment()
                }
            }
        }
    }
    jobs.joinAll()

    println("Final count: $count in ${time.passed} seconds")
}