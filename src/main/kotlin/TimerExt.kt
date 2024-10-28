package org.example

import java.time.Instant
import java.time.Instant.now

val Instant.passed
    get(): Float = now().minusMillis(this.toEpochMilli()).toEpochMilli().toFloat() / 1000