package com.ajith;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeRestControl {

	@GetMapping("/")
	private String wellcome() {
		return "welcome";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin")
	private String wellcomeA() {
		return "welcome Admin";
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/user")
	private String wellcomeu() {
		return "welcome User";
	}

}
