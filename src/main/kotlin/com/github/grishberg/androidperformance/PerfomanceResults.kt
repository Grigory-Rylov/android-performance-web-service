package com.github.grishberg.androidperformance

data class PerfomanceResults(
        val deviceName: String,
        val duration1: Float,
        val threadDuration1: Float,
        val duration2: Float,
        val threadDuration2: Float
)