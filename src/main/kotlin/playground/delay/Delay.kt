package org.example.playground.delay

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val elapsedTime = measureTimeMillis {
        val job1 = launch { delay(1_000) }
        val job2 = launch { delay(1_000) }
        joinAll(job1, job2)
    }
    println("Sleep time $elapsedTime")
}