package com.glinboy.dependencymonitor.repository

import com.glinboy.dependencymonitor.service.dto.DependencyDTO

interface DependencyRepository {
	fun getDependencies(): List<DependencyDTO>
	fun getDependencyById(id: Long): DependencyDTO?
	fun saveDependency(dependencyDTO: DependencyDTO): DependencyDTO?
	fun updateDependency(dependencyDTO: DependencyDTO): DependencyDTO?
	fun deleteDependency(id: Long): Int
}
