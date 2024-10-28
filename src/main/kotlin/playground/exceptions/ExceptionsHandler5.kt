package org.example.playground.exceptions

import kotlinx.coroutines.*

fun main() = runBlocking {
    val exceptionHandler = CoroutineExceptionHandler { _, _ -> print("!") }
    val scope = CoroutineScope(Job())

    val job = scope.launch(exceptionHandler) {
        supervisorScope {
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
    }

    job.join()
}