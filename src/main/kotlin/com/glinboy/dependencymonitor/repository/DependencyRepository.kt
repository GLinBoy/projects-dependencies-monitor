package com.glinboy.dependencymonitor.repository

import com.glinboy.dependencymonitor.service.dto.DependencyDTO
import com.glinboy.dependencymonitor.service.dto.VersionDTO

interface DependencyRepository {
	fun getDependencies(): List<DependencyDTO>
	fun getDependencyById(id: Long): DependencyDTO?
	fun saveDependency(dependencyDTO: DependencyDTO): DependencyDTO?
	fun updateDependency(dependencyDTO: DependencyDTO): DependencyDTO?
	fun deleteDependency(id: Long): Int
	fun getDependencyLatestVersion(id: Long): VersionDTO?
}
