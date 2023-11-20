package com.glinboy.dependencymonitor.repository.impl

import com.glinboy.dependencymonitor.entity.Tables
import com.glinboy.dependencymonitor.repository.ProjectRepository
import com.glinboy.dependencymonitor.service.dto.DependencyDTO
import com.glinboy.dependencymonitor.service.dto.ProjectDTO
import org.jooq.DSLContext
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
		dsl.selectFrom(Tables.PROJECT).where(Tables.PROJECT.ID.eq(id)).fetchOptional().map {
			ProjectDTO(
				it[Tables.PROJECT.ID],
				it[Tables.PROJECT.TITLE],
				it[Tables.PROJECT.CREATED_AT],
				it[Tables.PROJECT.UPDATED_AT]
			)
		}
			.orElseGet { null }

	override fun getProjectDependencies(id: Long): List<DependencyDTO> = dsl
		.selectDistinct(
			Tables.DEPENDENCY.ID,
			Tables.DEPENDENCY.TITLE,
			Tables.DEPENDENCY.CREATED_AT,
			Tables.DEPENDENCY.UPDATED_AT
		)
		.from(Tables.PROJECT)
		.leftJoin(Tables.PROJECT_DEPENDENCY).on(Tables.PROJECT_DEPENDENCY.PROJECT_ID.eq(Tables.PROJECT.ID))
		.leftJoin(Tables.VERSIONS).on(Tables.VERSIONS.ID.eq(Tables.PROJECT_DEPENDENCY.VERSION_ID))
		.leftJoin(Tables.DEPENDENCY).on(Tables.DEPENDENCY.ID.eq(Tables.VERSIONS.DEPENDENCY_ID))
		.where(Tables.PROJECT.ID.eq(id))
		.and(Tables.DEPENDENCY.ID.isNotNull)
		.fetchArray().map {
			DependencyDTO(
				it[Tables.DEPENDENCY.ID],
				it[Tables.DEPENDENCY.TITLE],
				it[Tables.DEPENDENCY.CREATED_AT],
				it[Tables.DEPENDENCY.UPDATED_AT]
			)
		}
		.toList()

	override fun saveProject(projectDTO: ProjectDTO): ProjectDTO? =
		dsl.insertInto(Tables.PROJECT, Tables.PROJECT.TITLE)
			.values(projectDTO.title)
			.returning()
			.fetchOneInto(ProjectDTO::class.java)

	override fun updateProject(projectDTO: ProjectDTO): ProjectDTO? =
		dsl.update(Tables.PROJECT)
			.set(Tables.PROJECT.TITLE, projectDTO.title)
			.set(Tables.PROJECT.UPDATED_AT, Instant.now())
			.where(Tables.PROJECT.ID.eq(projectDTO.id))
			.returning().fetchOneInto(ProjectDTO::class.java)

	override fun deleteProject(id: Long) = dsl.delete(Tables.PROJECT)
		.where(Tables.PROJECT.ID.eq(id))
		.execute()
}
