package com.example.library.service;

import com.example.library.model.Librarian;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.library.web.dto.LibrarianRegistrationDto;
import org.springframework.stereotype.Service;

@Service
public interface LibrarianService extends UserDetailsService {
	Librarian save(LibrarianRegistrationDto registrationDto);
}
