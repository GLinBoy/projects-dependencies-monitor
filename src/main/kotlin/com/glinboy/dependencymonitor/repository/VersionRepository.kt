package com.glinboy.dependencymonitor.repository

import com.glinboy.dependencymonitor.service.dto.VersionDTO

interface VersionRepository {
	fun getVersions(): List<VersionDTO>
	fun getVersionById(id: Long): VersionDTO?
}
