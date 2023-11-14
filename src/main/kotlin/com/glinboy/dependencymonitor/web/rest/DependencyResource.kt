package com.glinboy.dependencymonitor.web.rest

import com.glinboy.dependencymonitor.service.DependencyService
import com.glinboy.dependencymonitor.service.dto.DependencyDTO
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/dependencies")
class DependencyResource(private val service: DependencyService) {

	@GetMapping
	fun getDependencies() = service.getDependencies()
}
