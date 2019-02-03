package com.github.grishberg.androidperformance.controllers

import com.github.grishberg.androidperformance.ArrayResultsPrinter
import com.github.grishberg.androidperformance.PerfomanceResults
import com.github.grishberg.androidperformance.PerformanceConfiguration
import com.github.grishberg.performance.Log4JLogger
import com.github.grishberg.performance.PerformanceLauncher
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

@Controller
class PerformanceController {
    private val logger = Log4JLogger()

    @GetMapping("/perf")
    fun greetingForm(model: Model): String {
        model.addAttribute("configuration", PerformanceConfiguration())
        return "greeting"
    }

    @PostMapping("/perf")
    fun greetingSubmit(@ModelAttribute("configuration") configuration: PerformanceConfiguration): String {
        val launcher = getPerformanceLauncher(configuration.results)
        launcher.measurePerformance("java" == configuration.sourceType1,
                configuration.import1 ?: "",
                configuration.source1 ?: "",
                "java" == configuration.sourceType2,
                configuration.import2 ?: "",
                configuration.source2 ?: "")
        return "result"
    }

    @ModelAttribute("typeSingleSelectAllValues")
    fun getAllTypes() = arrayOf("java", "kotlin")

    private fun getPerformanceLauncher(resultsArray: ArrayList<PerfomanceResults>): PerformanceLauncher {
        val resultsHolder = ArrayResultsPrinter(resultsArray)
        return PerformanceLauncher(resultsHolder, logger)
    }
}