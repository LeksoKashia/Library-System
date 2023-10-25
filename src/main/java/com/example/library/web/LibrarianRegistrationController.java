package com.example.library.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.library.service.LibrarianService;
import com.example.library.web.dto.LibrarianRegistrationDto;

@Controller
@RequestMapping("/registration")
public class LibrarianRegistrationController {

	private LibrarianService librarianService;

	@Autowired
	public LibrarianRegistrationController(LibrarianService librarianService) {
		this.librarianService = librarianService;
	}

	@ModelAttribute("librarian")
	public LibrarianRegistrationDto librarianRegistrationDto() {
		return new LibrarianRegistrationDto();
	}

	@GetMapping
	public String showRegistrationForm() {
		return "authentication/registration";
	}

	@PostMapping
	public String registerlibrarianAccount(@ModelAttribute("librarian") LibrarianRegistrationDto registrationDto) {
		librarianService.save(registrationDto);
		return "redirect:/registration?success";
	}
}
