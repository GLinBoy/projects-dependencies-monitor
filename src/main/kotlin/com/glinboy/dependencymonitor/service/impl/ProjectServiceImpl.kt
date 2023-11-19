package com.glinboy.dependencymonitor.service.impl

import com.glinboy.dependencymonitor.repository.ProjectRepository
import com.glinboy.dependencymonitor.service.ProjectService
import com.glinboy.dependencymonitor.service.dto.DependencyDTO
import com.glinboy.dependencymonitor.service.dto.ProjectDTO
import org.springframework.stereotype.Service

@Service
class ProjectServiceImpl(val repository: ProjectRepository) : ProjectService {
	override fun getProjects(): List<ProjectDTO> = repository.getProjects()

	override fun getProjectById(id: Long) = repository.getProjectById(id)

	override fun getProjectDependencies(id: Long): List<DependencyDTO> = repository.getProjectDependencies(id)

	override fun saveProject(projectDTO: ProjectDTO): ProjectDTO? = repository.saveProject(projectDTO)

	override fun updateProject(projectDTO: ProjectDTO): ProjectDTO? = repository.updateProject(projectDTO)

	override fun deleteProject(id: Long) {
		repository.deleteProject(id)
	}
}
