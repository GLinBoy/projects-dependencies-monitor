package com.glinboy.dependencymonitor

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DependencyMonitorApplication

fun main(args: Array<String>) {
	runApplication<DependencyMonitorApplication>(*args)
}
