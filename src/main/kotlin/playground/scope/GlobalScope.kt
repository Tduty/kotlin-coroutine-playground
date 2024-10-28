package org.example.playground.scope

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
fun main() {
    suspend fun printx() {
        delay(100)
        print("x")
    }

    GlobalScope.run {
        // printx()
    }
}

// not compiled