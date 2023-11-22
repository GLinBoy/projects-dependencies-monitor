package com.glinboy.dependencymonitor.web.rest

import com.glinboy.dependencymonitor.service.DependencyService
import com.glinboy.dependencymonitor.service.VersionService
import com.glinboy.dependencymonitor.service.dto.DependencyDTO
import com.glinboy.dependencymonitor.service.dto.VersionDTO
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/dependencies")
class DependencyResource(private val service: DependencyService,
	private val versionService: VersionService) {

	@GetMapping
	fun getDependencies() = service.getDependencies()

	@GetMapping("/{id}")
	fun getDependencyById(@PathVariable id: Long): DependencyDTO? = service.getDependencyById(id)

	@GetMapping("/{id}/latest")
	fun getDependencyLatestVersion(@PathVariable id: Long): VersionDTO? = versionService.getDependencyLatestVersion(id)

	@PostMapping
	fun saveDependency(dependencyDTO: DependencyDTO) = service.saveDependency(dependencyDTO)

	@PutMapping
	fun updateDependency(dependencyDTO: DependencyDTO) = service.updateDependency(dependencyDTO)

	@DeleteMapping("/{id}")
	fun deleteDependency(@PathVariable id: Long) = service.deleteDependency(id)
}
