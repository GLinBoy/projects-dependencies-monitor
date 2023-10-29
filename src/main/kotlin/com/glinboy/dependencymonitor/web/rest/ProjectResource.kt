package com.glinboy.dependencymonitor.web.rest

import com.glinboy.dependencymonitor.service.ProjectService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/projects")
class ProjectResource(private val service: ProjectService) {

	@GetMapping
	fun getProjects() = service.getProjects()

	@GetMapping("/{id}")
	fun getProjectById(@PathVariable id: Long) {
		service.getProjectById(id)
	}
}
