package com.glinboy.dependencymonitor.service.dto

import java.time.Instant

data class ProjectDTO(
    val id: Long?,
    val title: String?,
    val createdAt: Instant?,
    val updatedAt: Instant?,
)
