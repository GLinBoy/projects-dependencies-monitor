package com.glinboy.dependencymonitor.service.dto

import java.time.Instant

data class VersionDTO(
	val id: Long?,
	val versionNumber: String?,
	val dependencyId: Instant?,
	val createdAt: Instant?,
	val updatedAt: Instant?
)
