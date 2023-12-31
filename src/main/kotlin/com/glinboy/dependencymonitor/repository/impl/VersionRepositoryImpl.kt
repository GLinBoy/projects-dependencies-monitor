package com.glinboy.dependencymonitor.repository.impl

import com.glinboy.dependencymonitor.entity.Tables
import com.glinboy.dependencymonitor.repository.VersionRepository
import com.glinboy.dependencymonitor.service.dto.VersionDTO
import org.jooq.DSLContext
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository
import java.time.Instant

@Repository
class VersionRepositoryImpl(private val dsl: DSLContext): VersionRepository {
	private val logger = LoggerFactory.getLogger(this::class.java)
	override fun getVersions(): List<VersionDTO> = dsl.select()
		.from(Tables.VERSIONS).fetch().map {
			VersionDTO(
				it[Tables.VERSIONS.ID],
				it[Tables.VERSIONS.VERSION_NUMBER],
				it[Tables.VERSIONS.DEPENDENCY_ID],
				it[Tables.VERSIONS.CREATED_AT],
				it[Tables.VERSIONS.UPDATED_AT]
			)
		}
		.toList()

	override fun getVersionById(id: Long): VersionDTO? =
		dsl.selectFrom(Tables.VERSIONS).where(Tables.VERSIONS.ID.eq(id)).fetchOptional().map {
			VersionDTO(
				it[Tables.VERSIONS.ID],
				it[Tables.VERSIONS.VERSION_NUMBER],
				it[Tables.VERSIONS.DEPENDENCY_ID],
				it[Tables.VERSIONS.CREATED_AT],
				it[Tables.VERSIONS.UPDATED_AT]
			)
		}
			.orElseGet { null }

	override fun saveVersion(versionDTO: VersionDTO): VersionDTO? =
		dsl.insertInto(Tables.VERSIONS)
			.set(Tables.VERSIONS.VERSION_NUMBER, versionDTO.versionNumber)
			.set(Tables.VERSIONS.DEPENDENCY_ID, versionDTO.dependencyId)
			.returning()
			.fetchOneInto(versionDTO::class.java)

	override fun updateVersion(versionDTO: VersionDTO): VersionDTO? =
		dsl.update(Tables.VERSIONS)
			.set(Tables.VERSIONS.VERSION_NUMBER, versionDTO.versionNumber)
			.set(Tables.VERSIONS.UPDATED_AT, Instant.now())
			.where(Tables.VERSIONS.ID.eq(versionDTO.id))
			.returning().fetchOneInto(versionDTO::class.java)

	override fun deleteVersion(id: Long) = dsl.delete(Tables.VERSIONS)
		.where(Tables.VERSIONS.ID.eq(id))
		.execute()

	override fun getDependencyLatestVersion(id: Long): VersionDTO? = dsl
		.select()
		.from(Tables.DEPENDENCY)
		.leftJoin(Tables.VERSIONS).on(Tables.VERSIONS.DEPENDENCY_ID.eq(Tables.DEPENDENCY.ID))
		.where(Tables.DEPENDENCY.ID.eq(id))
		.orderBy(Tables.DEPENDENCY.ID.desc())
		.limit(1)
		.fetchOneInto(VersionDTO::class.java)
}
