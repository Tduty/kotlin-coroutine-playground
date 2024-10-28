package org.example.playground.race

import kotlinx.coroutines.*
import org.example.passed
import java.time.Instant.now

private var count = 0

private fun increment() {
    count++
}

fun main() = runBlocking {
    val time = now()
    val jobs = List(1_000) {
        launch {
            repeat(1_000) {
                increment()
            }
        }
    }
    jobs.joinAll()

    println("Final count: $count in ${time.passed} seconds")
}

// 1_000_000
//
