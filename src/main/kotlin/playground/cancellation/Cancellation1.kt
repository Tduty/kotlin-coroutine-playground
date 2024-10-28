package org.example.playground.cancellation

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val job = launch {
        try {
            repeat(5) { i ->
                println("A$i")
                delay(100)
            }
        } finally {
            print("B")
        }
    }

    delay(250)
    print("C")
    job.cancelAndJoin()
    print("D")
}