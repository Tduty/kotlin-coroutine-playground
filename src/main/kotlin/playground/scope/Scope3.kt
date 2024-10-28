package org.example.playground.scope

import kotlinx.coroutines.*
import org.example.threadName

@OptIn(DelicateCoroutinesApi::class)
fun main() {
    runBlocking {
        val context1 = newFixedThreadPoolContext(1, "thread 1")
        val context2 = newFixedThreadPoolContext(1, "thread 2")

        launch(context1) {
            println("A $threadName")

            withContext(context2) {
                delay(500)
                println("B $threadName")
            }

            println("C $threadName")
        }
    }
}
