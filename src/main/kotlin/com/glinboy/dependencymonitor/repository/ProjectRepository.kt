package com.glinboy.dependencymonitor.repository

import com.glinboy.dependencymonitor.service.dto.ProjectDTO

interface ProjectRepository {
	fun getProjects(): List<ProjectDTO>
	fun getProjectById(id: Long): ProjectDTO?
	fun saveProject(projectDTO: ProjectDTO): ProjectDTO?
	fun updateProject(projectDTO: ProjectDTO): ProjectDTO?
}
