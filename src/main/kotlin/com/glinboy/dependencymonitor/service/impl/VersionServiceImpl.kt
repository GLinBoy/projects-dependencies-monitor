package com.glinboy.dependencymonitor.service.impl

import com.glinboy.dependencymonitor.repository.VersionRepository
import com.glinboy.dependencymonitor.service.VersionService
import com.glinboy.dependencymonitor.service.dto.VersionDTO
import org.springframework.stereotype.Service

@Service
class VersionServiceImpl(val repository: VersionRepository) : VersionService {
	override fun getVersions(): List<VersionDTO> = repository.getVersions()

	override fun getVersionById(id: Long) = repository.getVersionById(id)
}
