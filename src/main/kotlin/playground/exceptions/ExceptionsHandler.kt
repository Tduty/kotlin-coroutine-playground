package org.example.playground.exceptions

import kotlinx.coroutines.*

@OptIn(DelicateCoroutinesApi::class)
fun main() = runBlocking {
    val exceptionHandler = CoroutineExceptionHandler { _, _ -> print("!") }

    val job = GlobalScope.launch(exceptionHandler) {
        val child1 = launch {
            print("A")
            delay(500)
            print("B")
            throw RuntimeException()
        }

        val child2 = launch {
            print("C")
            delay(1000)
            print("2")
        }

        joinAll(child1, child2)
    }

    job.join()
}