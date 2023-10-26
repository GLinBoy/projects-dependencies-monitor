package com.glinboy.dependencymonitor.repository

import com.glinboy.dependencymonitor.service.dto.ProjectDTO

interface ProjectRepository {
	fun getProjects(): List<ProjectDTO>
}
