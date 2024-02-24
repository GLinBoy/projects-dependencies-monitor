package com.glinboy.dependencymonitor.web.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/projects")
class ProjectController {

	@GetMapping
	fun getProjectsListPage(): String {
		return "project/index"
	}

}
