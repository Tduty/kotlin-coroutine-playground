package org.example.playground.exceptions

import kotlinx.coroutines.*

fun main() = runBlocking {
    val scope = CoroutineScope(Job())

    val job1 = scope.launch {
        print("A")
        delay(500)
        print("B")
        throw RuntimeException()
    }

    val job2 = scope.launch {
        print("C")
        delay(1000)
        print("2")
    }

    joinAll(job1, job2)

    println("D")
}