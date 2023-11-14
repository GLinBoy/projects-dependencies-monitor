package com.glinboy.dependencymonitor.service

import com.glinboy.dependencymonitor.service.dto.DependencyDTO

interface DependencyService {
	fun getDependencies(): List<DependencyDTO>
	fun getDependencyById(id: Long): DependencyDTO?
	fun saveDependency(dependencyDTO: DependencyDTO): DependencyDTO?
}
