package com.github.grishberg.androidperformance

data class PerformanceConfiguration(
        val sourceType1: String? = null,
        val sourceType2: String? = null,
        val import1: String? = null,
        val init1: String? = null,
        val fields1: String? = null,
        val import2: String? = null,
        val init2: String? = null,
        val fields2: String? = null,
        val source1: String? = null,
        val source2: String? = null,
        val launchesCount: Int = 1,
        val iterationsPerLaunchCount: Int = 500000,
        val results: ArrayList<PerfomanceResults> = ArrayList()
)