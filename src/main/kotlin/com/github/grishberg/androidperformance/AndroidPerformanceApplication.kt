package com.github.grishberg.androidperformance

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
class AndroidPerformanceApplication

fun main(args: Array<String>) {
    //runApplication<AndroidPerformanceApplication>(*args)
    SpringApplication.run(AndroidPerformanceApplication::class.java, *args)
}

