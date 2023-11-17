package com.glinboy.dependencymonitor.service

import com.glinboy.dependencymonitor.service.dto.VersionDTO

interface VersionService {
	fun getVersions(): List<VersionDTO>
	fun getVersionById(id: Long): VersionDTO?
}
