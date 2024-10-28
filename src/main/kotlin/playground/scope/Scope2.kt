package org.example.playground.scope

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    val job1 = launch {
        delay(500)
        print("A")
    }

    val job2 = coroutineScope {
        launch {
            delay(1000)
            print("B")
        }
    }
    print("C")

}

// ABC