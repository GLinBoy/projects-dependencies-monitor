package com.glinboy.dependencymonitor.repository.impl

import com.glinboy.dependencymonitor.repository.ProjectRepository
import org.jooq.DSLContext
import org.springframework.stereotype.Repository

@Repository
class ProjectRepositoryImpl(private val dsl: DSLContext) : ProjectRepository {
}
