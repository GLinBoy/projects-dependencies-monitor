package com.glinboy.dependencymonitor.repository.impl

import com.glinboy.dependencymonitor.entity.Tables
import com.glinboy.dependencymonitor.repository.DependencyRepository
import com.glinboy.dependencymonitor.service.dto.DependencyDTO
import com.glinboy.dependencymonitor.service.dto.VersionDTO
import org.jooq.DSLContext
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository
import java.time.Instant

@Repository
class DependencyRepositoryImpl(private val dsl: DSLContext) : DependencyRepository {

	private val logger = LoggerFactory.getLogger(this::class.java)
	override fun getDependencies(): List<DependencyDTO> = dsl.select()
		.from(Tables.DEPENDENCY).fetch().map {
			DependencyDTO(
				it[Tables.DEPENDENCY.ID],
				it[Tables.DEPENDENCY.TITLE],
				it[Tables.DEPENDENCY.CREATED_AT],
				it[Tables.DEPENDENCY.UPDATED_AT]
			)
		}
		.toList()

	override fun getDependencyById(id: Long): DependencyDTO? =
		dsl.selectFrom(Tables.DEPENDENCY).where(Tables.DEPENDENCY.ID.eq(id)).fetchOptional().map {
			DependencyDTO(
				it[Tables.DEPENDENCY.ID],
				it[Tables.DEPENDENCY.TITLE],
				it[Tables.DEPENDENCY.CREATED_AT],
				it[Tables.DEPENDENCY.UPDATED_AT]
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

	override fun saveDependency(dependencyDTO: DependencyDTO): DependencyDTO? =
		dsl.insertInto(Tables.DEPENDENCY, Tables.DEPENDENCY.TITLE)
			.values(dependencyDTO.title)
			.returning()
			.fetchOneInto(dependencyDTO::class.java)

	override fun updateDependency(dependencyDTO: DependencyDTO): DependencyDTO? =
		dsl.update(Tables.DEPENDENCY)
			.set(Tables.DEPENDENCY.TITLE, dependencyDTO.title)
			.set(Tables.DEPENDENCY.UPDATED_AT, Instant.now())
			.where(Tables.DEPENDENCY.ID.eq(dependencyDTO.id))
			.returning().fetchOneInto(dependencyDTO::class.java)

	override fun deleteDependency(id: Long) = dsl.delete(Tables.DEPENDENCY)
		.where(Tables.DEPENDENCY.ID.eq(id))
		.execute()
}
