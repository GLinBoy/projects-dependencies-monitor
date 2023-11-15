package com.glinboy.dependencymonitor.service.impl

import com.glinboy.dependencymonitor.repository.DependencyRepository
import com.glinboy.dependencymonitor.service.DependencyService
import com.glinboy.dependencymonitor.service.dto.DependencyDTO
import org.springframework.stereotype.Service

@Service
class DependencyServiceImpl(private val repository: DependencyRepository) : DependencyService {
	override fun getDependencies(): List<DependencyDTO> = repository.getDependencies()

	override fun getDependencyById(id: Long) = repository.getDependencyById(id)
}
