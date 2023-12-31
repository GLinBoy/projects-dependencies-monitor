package com.glinboy.dependencymonitor.web.rest

import com.glinboy.dependencymonitor.service.VersionService
import com.glinboy.dependencymonitor.service.dto.VersionDTO
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/versions")
class VersionResource(private val service: VersionService) {

	@GetMapping
	fun getVersions() = service.getVersions()

	@GetMapping("/{id}")
	fun getVersionById(@PathVariable id: Long): VersionDTO? {
		return service.getVersionById(id)
	}

	@PostMapping
	fun saveVersion(versionDTO: VersionDTO) = service.saveVersion(versionDTO)

	@PutMapping
	fun updateVersion(versionDTO: VersionDTO) = service.updateVersion(versionDTO)

	@DeleteMapping("/{id}")
	fun deleteVersion(@PathVariable id: Long) = service.deleteVersion(id)
}
