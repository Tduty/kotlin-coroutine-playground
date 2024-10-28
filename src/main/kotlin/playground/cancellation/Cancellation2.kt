package org.example.playground.cancellation

import kotlinx.coroutines.*

fun main() = runBlocking {
    val supervisor = SupervisorJob()

    with(CoroutineScope(coroutineContext + supervisor)) {
        val job = launch {
            try {
                print("A")
                delay(Long.MAX_VALUE)
            } finally {
                print("B")
            }
        }

        delay(1000)
        print("C")
        supervisor.cancel()
    }
    print("D")
}