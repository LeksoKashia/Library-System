package com.example.library.service;

import java.util.ArrayList;
import java.util.List;

import com.example.library.model.Librarian;
import com.example.library.repository.LibrarianRepository;
import com.example.library.web.dto.LibrarianRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LibrarianServiceImpl implements LibrarianService {

	private LibrarianRepository librarianRepository;
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public LibrarianServiceImpl(LibrarianRepository librarianRepository, BCryptPasswordEncoder passwordEncoder) {
		this.librarianRepository = librarianRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Librarian save(LibrarianRegistrationDto registrationDto) {
		Librarian librarian = new Librarian(
				registrationDto.getFirstName(),
				registrationDto.getLastName(),
				registrationDto.getEmail(),
				passwordEncoder.encode(registrationDto.getPassword())
		);
		return librarianRepository.save(librarian);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Librarian librarian = librarianRepository.findByEmail(username);
		if (librarian == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(librarian.getEmail(), librarian.getPassword(), new ArrayList<>());
	}

	public void updateExistingUserPasswords() {
		List<Librarian> librarians = librarianRepository.findAll();
		for (Librarian librarian : librarians) {
			if (!passwordEncoder.matches(librarian.getPassword(), librarian.getPassword())) {
				// If the password is not already encoded, encode it
				librarian.setPassword(passwordEncoder.encode(librarian.getPassword()));
				librarianRepository.save(librarian);
			}
		}
	}


	
}
