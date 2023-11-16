package com.glinboy.dependencymonitor.repository.impl

import com.glinboy.dependencymonitor.entity.Tables
import com.glinboy.dependencymonitor.repository.DependencyRepository
import com.glinboy.dependencymonitor.service.dto.DependencyDTO
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
}
