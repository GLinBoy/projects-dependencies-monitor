package com.glinboy.dependencymonitor.repository

import com.glinboy.dependencymonitor.service.dto.VersionDTO

interface VersionRepository {
	fun getVersions(): List<VersionDTO>
	fun getVersionById(id: Long): VersionDTO?
	fun saveVersion(versionDTO: VersionDTO): VersionDTO?
	fun updateVersion(versionDTO: VersionDTO): VersionDTO?
	fun deleteVersion(id: Long): Int
	fun getDependencyLatestVersion(id: Long): VersionDTO?
}
