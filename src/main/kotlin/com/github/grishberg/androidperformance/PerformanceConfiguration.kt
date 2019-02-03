package com.github.grishberg.androidperformance

data class PerformanceConfiguration(
        val sourceType1: String? = null,
        val sourceType2: String? = null,
        val import1: String? = null,
        val import2: String? = null,
        val source1: String? = null,
        val source2: String? = null,
        val results: ArrayList<PerfomanceResults> = ArrayList()
)