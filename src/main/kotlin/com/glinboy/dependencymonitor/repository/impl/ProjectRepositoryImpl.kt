package com.glinboy.dependencymonitor.repository.impl

import com.glinboy.dependencymonitor.entity.Tables
import com.glinboy.dependencymonitor.repository.ProjectRepository
import com.glinboy.dependencymonitor.service.dto.ProjectDTO
import org.jooq.DSLContext
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository

@Repository
class ProjectRepositoryImpl(private val dsl: DSLContext) : ProjectRepository {
	private val logger = LoggerFactory.getLogger(this::class.java)
	override fun getProjects(): List<ProjectDTO> = dsl.select()
		.from(Tables.PROJECT).fetch().map {
			ProjectDTO(
				it[Tables.PROJECT.ID],
				it[Tables.PROJECT.TITLE],
				it[Tables.PROJECT.CREATED_AT],
				it[Tables.PROJECT.UPDATED_AT]
			)
		}
		.toList()

	override fun getProjectById(id: Long): ProjectDTO? =
		dsl.selectFrom(Tables.PROJECT).where(Tables.PROJECT.ID.eq(id)).fetchOptional().map {
			ProjectDTO(
				it[Tables.PROJECT.ID],
				it[Tables.PROJECT.TITLE],
				it[Tables.PROJECT.CREATED_AT],
				it[Tables.PROJECT.UPDATED_AT]
			)
		}
			.orElseGet { null }

}
