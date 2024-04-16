package com.example.iatstages.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@AllArgsConstructor
@RequestMapping("/api/test")
public class TestController {
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}

	@GetMapping("/candidat")
	@PreAuthorize("hasRole('CANDIDAT') or hasRole('ADMIN')")
	public String candidatAccess() {
		return "CANDIDAT Content.";
	}

	@GetMapping("/recruteur")
	@PreAuthorize("hasRole('RECRUTEUR') or hasRole('ADMIN')")
	public String recruteurAccess() {
		return "RECRUTEUR Content.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
}
