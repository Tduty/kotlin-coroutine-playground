package org.example.playground.race

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit

private var count = 0
private val semaphore = Semaphore(1)

private fun increment() {
    count++
}

private fun main() = runBlocking {
    val jobs = List(1_000) {
        launch(Dispatchers.Default) {
            repeat(1_000) {
                semaphore.withPermit {
                    increment()
                }
            }
        }
    }
    jobs.joinAll()
    println(count)
}