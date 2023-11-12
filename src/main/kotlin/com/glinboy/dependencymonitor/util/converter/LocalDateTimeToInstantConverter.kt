package com.glinboy.dependencymonitor.util.converter

import org.jooq.impl.AbstractConverter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

class LocalDateTimeToInstantConverter : AbstractConverter<LocalDateTime, Instant>(
	LocalDateTime::class.java,
	Instant::class.java
) {
	override fun from(databaseObject: LocalDateTime?): Instant? = databaseObject?.toInstant(ZoneOffset.UTC)

	override fun to(userObject: Instant?): LocalDateTime? = userObject?.atZone(ZoneOffset.UTC)?.toLocalDateTime()

}
