import nu.studer.gradle.jooq.JooqEdition
import nu.studer.gradle.jooq.JooqGenerate
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jooq.meta.jaxb.Logging

plugins {
	id("org.springframework.boot") version "3.1.5"
	id("io.spring.dependency-management") version "1.1.3"
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22"
	id("org.ec4j.editorconfig") version "0.0.3"
	id("org.flywaydb.flyway") version "9.22.3"
	id("nu.studer.jooq") version "8.2.1"
}

group = "com.glinboy"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

val flywayMigration = configurations.create("flywayMigration")

val springDocVersion = "2.2.0"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-jooq")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.flywaydb:flyway-core")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${springDocVersion}")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.h2database:h2")
	flywayMigration("com.h2database:h2")
	jooqGenerator("com.h2database:h2")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

flyway {
	configurations = arrayOf("flywayMigration")
	url = "jdbc:h2:~/test;AUTO_SERVER=TRUE"
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
	inputs.files(fileTree("src/main/resources/db/migration"))
		.withPropertyName("migrations")
		.withPathSensitivity(PathSensitivity.RELATIVE)
	allInputsDeclared.set(true)
}
