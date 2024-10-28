package org.example.playground.timeout

import kotlinx.coroutines.*

fun main() = runBlocking {
    withTimeout(1300L) {
        repeat(5) { i ->
            print("$i")
            delay(500)
        }
    }

    print("ok")
}