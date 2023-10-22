package com.glinboy.dependencymonitor.service.impl

import com.glinboy.dependencymonitor.repository.ProjectRepository
import com.glinboy.dependencymonitor.service.ProjectService
import org.springframework.stereotype.Service

@Service
class ProjectServiceImpl(val respository: ProjectRepository) : ProjectService {
}
