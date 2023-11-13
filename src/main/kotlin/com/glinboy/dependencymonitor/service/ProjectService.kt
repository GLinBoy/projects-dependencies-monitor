package com.glinboy.dependencymonitor.service

import com.glinboy.dependencymonitor.service.dto.ProjectDTO

interface ProjectService {
	fun getProjects(): List<ProjectDTO>
	fun getProjectById(id: Long): ProjectDTO?
	fun saveProject(projectDTO: ProjectDTO): ProjectDTO?
	fun updateProject(projectDTO: ProjectDTO): ProjectDTO?
	fun deleteProject(id: Long)
}
