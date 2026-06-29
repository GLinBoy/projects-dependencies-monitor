import nu.studer.gradle.jooq.JooqEdition
import nu.studer.gradle.jooq.JooqGenerate
import org.jooq.meta.jaxb.ForcedType
import org.jooq.meta.jaxb.Logging
import java.time.Instant

plugins {
	kotlin("jvm") version "2.3.21"
	kotlin("plugin.spring") version "2.3.21"
	id("org.springframework.boot") version "4.1.0"
	id("io.spring.dependency-management") version "1.1.7"
	id("org.ec4j.editorconfig") version "0.1.0"
	id("org.flywaydb.flyway") version "12.9.0"
	id("nu.studer.jooq") version "10.2.1"
	id("com.github.ben-manes.versions") version "0.54.0"
}

group = "com.glinboy"
version = "0.0.1-SNAPSHOT"

defaultTasks("bootRun")

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(25)
	}
}

repositories {
	mavenCentral()
}

val flywayMigration = configurations.create("flywayMigration")

val springDocVersion = "3.0.3"

dependencies {
	implementation("org.springframework.boot:spring-boot-h2console")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-flyway")
	implementation("org.springframework.boot:spring-boot-starter-jooq")
	implementation("org.springframework.boot:spring-boot-starter-webmvc")
	implementation("org.flywaydb:flyway-database-postgresql")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("tools.jackson.module:jackson-module-kotlin")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${springDocVersion}")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.h2database:h2")
	flywayMigration("com.h2database:h2")
	jooqGenerator("com.h2database:h2")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-actuator-test")
	testImplementation("org.springframework.boot:spring-boot-starter-flyway-test")
	testImplementation("org.springframework.boot:spring-boot-starter-jooq-test")
	testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict", "-Xannotation-default-target=param-property")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

flyway {
	configurations = arrayOf("flywayMigration")
	url = "jdbc:h2:${project.layout.buildDirectory.get().asFile.absolutePath}/dm;AUTO_SERVER=TRUE;MODE=PostgreSQL;"
	user = "sa"
	password = ""
}

jooq {
	version.set(dependencyManagement.importedProperties["jooq.version"])
	edition.set(JooqEdition.OSS)
	configurations {
		create("main") {
			jooqConfiguration.apply {
				logging = Logging.INFO
				jdbc.apply {
					driver = "org.h2.Driver"
					url = flyway.url
					user = flyway.user
					password = flyway.password
				}

				generator.apply {
					name = "org.jooq.codegen.DefaultGenerator"
					database.apply {
						name = "org.jooq.meta.h2.H2Database"
						inputSchema = "PUBLIC"
						includes = ".*"
						excludes = ""

						isIncludeTables = true
						isIncludePackages = false
						isIncludeUDTs = true
						isIncludeSequences = true
						isIncludePrimaryKeys = true
						isIncludeUniqueKeys = true
						isIncludeForeignKeys = true
						forcedTypes = listOf(
							ForcedType().apply {
								userType = Instant::class.java.name
								includeTypes = "TIMESTAMP"
								converter = "com.glinboy.dependencymonitor.util.converter.LocalDateTimeToInstantConverter"
							}
						)
					}

					target.apply {
						packageName = "com.glinboy.dependencymonitor.entity"
					}
				}
			}
		}
	}
}

tasks.named<JooqGenerate>("generateJooq").configure {
	dependsOn(tasks.named("flywayMigrate"))
	inputs.files(fileTree("src/main/db/migration"))
		.withPropertyName("migrations")
		.withPathSensitivity(PathSensitivity.RELATIVE)
	allInputsDeclared.set(true)
}

tasks.bootRun {
	jvmArgs = listOf(
		"-XX:+UseZGC",
		"-Xlog:gc*:file=logs/dm.gc.log",
		"-XX:+HeapDumpOnOutOfMemoryError",
		"-XX:HeapDumpPath=log/crashes/dm.hprof",
		"-Xss256k",
		"-Dsun.net.client.defaultConnectTimeout=2000",
		"-Dsun.net.client.defaultReadTimeout=2000"
	)
}
