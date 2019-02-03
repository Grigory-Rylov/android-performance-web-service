package com.github.grishberg.androidperformance

import com.github.grishberg.performance.ResultsPrinter
import com.github.grishberg.tests.ConnectedDeviceWrapper

class ArrayResultsPrinter(
        private val resultsArray: ArrayList<PerfomanceResults>
) : ResultsPrinter {
    override fun populateResult(device: ConnectedDeviceWrapper,
                                duration1: Long, threadDuration1:
                                Long, duration2: Long, threadDuration2: Long) {
        resultsArray.add(PerfomanceResults(device.name, duration1, threadDuration1,
                duration2, threadDuration2))
    }

    override fun results(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}