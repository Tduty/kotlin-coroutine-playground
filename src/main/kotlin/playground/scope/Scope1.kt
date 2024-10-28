package org.example.playground.scope

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val job1 = launch {
        print("A")
    }
    val job2 = launch {
        print("B")
    }
    print("C")
}

// CAB
