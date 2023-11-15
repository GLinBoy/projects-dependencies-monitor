package com.glinboy.dependencymonitor.repository

import com.glinboy.dependencymonitor.service.dto.DependencyDTO

interface DependencyRepository {
	fun getDependencies(): List<DependencyDTO>
}
