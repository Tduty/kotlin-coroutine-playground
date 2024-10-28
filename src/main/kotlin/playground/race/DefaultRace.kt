package org.example.playground.race

import kotlinx.coroutines.*

private var count = 0

private fun increment() {
    count++
}

fun main() = runBlocking {
    val jobs = List(1_000) {
        launch(Dispatchers.Default) {
            repeat(1_000) {
                increment()
            }
        }
    }
    jobs.joinAll()

    println("Final count: $count")
}
