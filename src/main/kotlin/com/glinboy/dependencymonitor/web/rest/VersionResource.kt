package com.glinboy.dependencymonitor.web.rest

import com.glinboy.dependencymonitor.service.VersionService
import com.glinboy.dependencymonitor.service.dto.VersionDTO
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/versions")
class VersionResource(private val service: VersionService) {

	@GetMapping
	fun getVersions() = service.getVersions()
}
