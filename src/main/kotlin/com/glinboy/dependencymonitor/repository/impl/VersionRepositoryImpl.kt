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
}
