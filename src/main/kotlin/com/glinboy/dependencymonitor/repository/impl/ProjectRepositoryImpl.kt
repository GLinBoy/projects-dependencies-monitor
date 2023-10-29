package com.glinboy.dependencymonitor.repository.impl

import com.glinboy.dependencymonitor.entity.Tables
import com.glinboy.dependencymonitor.repository.ProjectRepository
import com.glinboy.dependencymonitor.service.dto.ProjectDTO
import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository
import java.time.Instant

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
		dsl.selectFrom("PROJECT").where(DSL.field("ID").eq(id)).fetchOptional().map {
			ProjectDTO(it["ID"] as Long, it["TITLE"] as String, it["CREATED_AT"] as Instant, it["UPDATED_AT"] as Instant)
		}
			.orElseGet { null }

}
