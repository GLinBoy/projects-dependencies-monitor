package com.glinboy.dependencymonitor.service.impl

import com.glinboy.dependencymonitor.repository.DependencyRepository
import com.glinboy.dependencymonitor.service.DependencyService
import com.glinboy.dependencymonitor.service.dto.DependencyDTO
import com.glinboy.dependencymonitor.service.dto.VersionDTO
import org.springframework.stereotype.Service

@Service
class DependencyServiceImpl(private val repository: DependencyRepository) : DependencyService {
	override fun getDependencies(): List<DependencyDTO> = repository.getDependencies()

	override fun getDependencyById(id: Long) = repository.getDependencyById(id)

	override fun getProjectDependencies(id: Long): List<DependencyDTO> = repository.getProjectDependencies(id)

	override fun saveDependency(dependencyDTO: DependencyDTO): DependencyDTO? = repository.saveDependency(dependencyDTO)

	override fun updateDependency(dependencyDTO: DependencyDTO): DependencyDTO? =
		repository.updateDependency(dependencyDTO)

	override fun deleteDependency(id: Long) {
		repository.deleteDependency(id)
	}
}
