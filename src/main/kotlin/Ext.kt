package org.example

val Int.seconds: Long
    get() = this.times(1000L)

val threadName
    get() = Thread.currentThread().name