package org.example.playground.race

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.atomic.AtomicInteger

private var count = AtomicInteger(0)

private fun increment() = repeat(1_000) {
    count.getAndIncrement()
}

fun main() = runBlocking {
    List(1_000) {
        launch(Dispatchers.Default) { increment() }
    }.onEach { it.join() }
    println(count)
}