package org.example.conf

import kotlinx.coroutines.*
import org.example.passed
import java.time.Instant.now

private var count = 0

private fun increment() {
    count++
}

fun noRaceExample() = runBlocking {
    val jobs = List(1_000) {
        launch {
            repeat(1_000) {
                increment()
            }
        }
    }
    jobs.joinAll()

    println("Final count: $count")
}

fun main() = noRaceExample()

// Варианты ответов:
// 1_000_000
// 0 - 1_000_000
// not compiled
// Exception

// Ответ: в launch передается empty context