package com.glinboy.dependencymonitor.web.rest

import com.glinboy.dependencymonitor.service.ProjectService
import com.glinboy.dependencymonitor.service.dto.ProjectDTO
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/projects")
class ProjectResource(private val service: ProjectService) {

	@GetMapping
	fun getProjects() = service.getProjects()

	@GetMapping("/{id}")
	fun getProjectById(@PathVariable id: Long): ProjectDTO? {
		return service.getProjectById(id)
	}

	@PostMapping
	fun saveProject(projectDTO: ProjectDTO) = service.saveProject(projectDTO)

	@PutMapping
	fun updateProject(projectDTO: ProjectDTO) = service.updateProject(projectDTO)

	@DeleteMapping("/{id}")
	fun deleteProject(@PathVariable id: Long) = service.deleteProject(id)
}
