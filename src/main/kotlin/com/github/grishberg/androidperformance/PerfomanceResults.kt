package com.github.grishberg.androidperformance

data class PerfomanceResults(
        val deviceName: String,
        val duration1: Long,
        val threadDuration1: Long,
        val duration2: Long,
        val threadDuration2: Long
)