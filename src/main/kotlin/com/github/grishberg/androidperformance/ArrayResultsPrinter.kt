package com.github.grishberg.androidperformance

import com.github.grishberg.performance.ResultsPrinter
import com.github.grishberg.tests.ConnectedDeviceWrapper

class ArrayResultsPrinter(
        private val resultsArray: ArrayList<PerfomanceResults>
) : ResultsPrinter {
    private val initialState: State = Initial()
    private val measurementState: State = MeasurementState()
    private var state: State = initialState
    private var duration1: Long = 0
    private var duration2: Long = 0
    private var microDuration1: Long = 0
    private var microDuration2: Long = 0
    override fun populateResult(experimentNumber: Int, device: ConnectedDeviceWrapper, threadDuration: Long, microDuration: Long) {
        state.populateResult(experimentNumber, device, threadDuration, microDuration)
    }

    override fun results(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private inner class Initial : State {
        override fun populateResult(experimentNumber: Int,
                                    device: ConnectedDeviceWrapper,
                                    threadDuration: Long, microDuration: Long) {
            state = measurementState
            duration1 = threadDuration
            microDuration1 = microDuration
        }
    }

    private inner class MeasurementState : State {
        override fun populateResult(experimentNumber: Int,
                                    device: ConnectedDeviceWrapper,
                                    threadDuration: Long, microDuration: Long) {
            state = initialState
            duration2 = threadDuration
            microDuration2 = microDuration
            resultsArray.add(PerfomanceResults(device.name,
                    duration1, microDuration1,
                    duration2, microDuration2))
        }
    }

    private interface State {
        fun populateResult(experimentNumber: Int, device: ConnectedDeviceWrapper, threadDuration: Long, microDuration: Long) = Unit
    }
}