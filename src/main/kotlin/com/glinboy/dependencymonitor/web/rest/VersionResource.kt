package com.glinboy.dependencymonitor.web.rest

import com.glinboy.dependencymonitor.service.VersionService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/versions")
class VersionResource(private val service: VersionService) {
}
