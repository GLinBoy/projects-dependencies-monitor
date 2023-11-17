package com.glinboy.dependencymonitor.service.dto

import java.time.Instant

data class VersionDTO(
	val id: Long?,
	val versionNumber: String?,
	val dependencyId: Long?,
	val createdAt: Instant?,
	val updatedAt: Instant?
)
