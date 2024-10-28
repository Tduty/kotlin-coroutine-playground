package org.example.playground.race

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

sealed class CounterMsg {
    data object Increment : CounterMsg()
    data class GetCounter(val response: CompletableDeferred<Int>) : CounterMsg()
}

@OptIn(ObsoleteCoroutinesApi::class)
private fun CoroutineScope.counterActor(): SendChannel<CounterMsg> = actor {
    var counter = 0
    val receiver = launch {
        for (msg in channel) {
            when (msg) {
                is CounterMsg.Increment -> counter++
                is CounterMsg.GetCounter -> msg.response.complete(counter)
            }
        }
    }
    receiver.join()
}

private suspend fun increment(channel: SendChannel<CounterMsg>) = repeat(1000) {
    channel.send(CounterMsg.Increment)
}

fun main() = runBlocking {
    val counterActor = counterActor()

    List(1_000) {
        launch(Dispatchers.Default) { increment(counterActor) }
    }.onEach { it.join() }

    val response = CompletableDeferred<Int>()
    counterActor.send(CounterMsg.GetCounter(response))
    counterActor.close()
    val result = response.await()
    println(result)
}