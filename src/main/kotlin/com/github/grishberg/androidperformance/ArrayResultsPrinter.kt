package com.github.grishberg.androidperformance

import com.github.grishberg.performance.ResultsPrinter
import com.github.grishberg.tests.ConnectedDeviceWrapper

/**
 * Calculates min max duration.
 */
class ArrayResultsPrinter(
        private val launchesCount: Int,
        private val resultsArray: ArrayList<PerfomanceResults>
) : ResultsPrinter {
    private val initialState: State = Initial()
    private val measurementState: State = MeasurementState()
    private val lastMeasurementState: State = LastMeasureState()

    private var state: State = initialState
    private var duration1: Long = 0
    private var duration2: Long = 0
    private var microDuration1: Long = 0
    private var microDuration2: Long = 0
    private var minDuration1 = Long.MAX_VALUE
    private var maxDuration1 = 0L
    private var minDuration2 = Long.MAX_VALUE
    private var maxDuration2 = 0L
    private var midDuration1 = 0L
    private var midDuration2 = 0L
    private var midMicroDuration1 = 0L
    private var midMicroDuration2 = 0L

    private var launchIndex = 0

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
            launchIndex++

            duration1 = threadDuration
            microDuration1 = microDuration
            calculateMinMaxForFistExperiment(threadDuration, microDuration)

            state = if (launchIndex == launchesCount) {
                lastMeasurementState
            } else {
                measurementState
            }
        }

        private fun calculateMinMaxForFistExperiment(threadDuration: Long, microDuration: Long) {
            if (threadDuration > maxDuration1) {
                maxDuration1 = threadDuration
            } else if (threadDuration < minDuration1) {
                microDuration1 = threadDuration
            }
            midDuration1 += threadDuration
            midMicroDuration1 += microDuration
        }
    }

    private inner class MeasurementState : State {
        override fun populateResult(experimentNumber: Int,
                                    device: ConnectedDeviceWrapper,
                                    threadDuration: Long, microDuration: Long) {
            state = initialState
            duration2 = threadDuration
            microDuration2 = microDuration
            calculateMinMaxForSecondExperiment(threadDuration, microDuration)
        }

    }

    private inner class LastMeasureState : State {
        override fun populateResult(experimentNumber: Int, device: ConnectedDeviceWrapper, threadDuration: Long, microDuration: Long) {
            calculateMinMaxForSecondExperiment(threadDuration, microDuration)

            resultsArray.add(PerfomanceResults(device.name,
                    midDuration1.toFloat() / launchesCount.toFloat(),
                    midMicroDuration1 / launchesCount.toFloat(),
                    midDuration2.toFloat() / launchesCount.toFloat(),
                    midMicroDuration2 / launchesCount.toFloat()))
        }
    }

    private fun calculateMinMaxForSecondExperiment(threadDuration: Long, microDuration: Long) {
        if (threadDuration > maxDuration2) {
            maxDuration2 = threadDuration
        } else if (threadDuration < minDuration2) {
            microDuration2 = threadDuration
        }
        midDuration2 += threadDuration
        midMicroDuration2 += microDuration
    }

    private interface State {
        fun populateResult(experimentNumber: Int, device: ConnectedDeviceWrapper, threadDuration: Long, microDuration: Long) = Unit
    }
}