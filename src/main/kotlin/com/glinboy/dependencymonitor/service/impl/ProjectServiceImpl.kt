package com.glinboy.dependencymonitor.service.impl

import com.glinboy.dependencymonitor.repository.ProjectRepository
import com.glinboy.dependencymonitor.service.ProjectService
import com.glinboy.dependencymonitor.service.dto.ProjectDTO
import org.springframework.stereotype.Service

@Service
class ProjectServiceImpl(val repository: ProjectRepository) : ProjectService {
	override fun getProjects(): List<ProjectDTO> = repository.getProjects()

	override fun getProjectById(id: Long) = repository.getProjectById(id)
}
