package com.glinboy.dependencymonitor.util.converter

import org.jooq.Converter
import java.sql.Timestamp
import java.time.Instant

class TimestampInstantConverter: Converter<Timestamp, Instant> {
	override fun from(databaseObject: Timestamp?): Instant {
		TODO("Not yet implemented")
	}

	override fun to(userObject: Instant?): Timestamp {
		TODO("Not yet implemented")
	}

	override fun fromType(): Class<Timestamp> {
		TODO("Not yet implemented")
	}

	override fun toType(): Class<Instant> {
		TODO("Not yet implemented")
	}
}
