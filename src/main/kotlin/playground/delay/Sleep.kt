package org.example.playground.delay

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.joinAll
import org.example.seconds
import java.lang.Thread.sleep
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val elapsedTime = measureTimeMillis {
        val job1 = launch { sleep(1.seconds) }
        val job2 = launch { sleep(1.seconds) }
        joinAll(job1, job2)
    }
    println("Sleep time $elapsedTime")
}
