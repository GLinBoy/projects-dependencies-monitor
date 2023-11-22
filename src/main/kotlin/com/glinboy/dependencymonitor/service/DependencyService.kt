package com.glinboy.dependencymonitor.service

import com.glinboy.dependencymonitor.service.dto.DependencyDTO
import com.glinboy.dependencymonitor.service.dto.VersionDTO

interface DependencyService {
	fun getDependencies(): List<DependencyDTO>
	fun getDependencyById(id: Long): DependencyDTO?
	fun getProjectDependencies(id: Long): List<DependencyDTO>
	fun saveDependency(dependencyDTO: DependencyDTO): DependencyDTO?
	fun updateDependency(dependencyDTO: DependencyDTO): DependencyDTO?
	fun deleteDependency(id: Long)
	fun getDependencyLatestVersion(id: Long): VersionDTO?
}
