package com.glinboy.dependencymonitor.web.rest

import com.glinboy.dependencymonitor.service.DependencyService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/dependencies")
class DependencyResource(private val service: DependencyService) {
}
