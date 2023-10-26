package com.example.library;

import com.example.library.model.Book;
import com.example.library.model.Librarian;
import com.example.library.repository.BookRepository;
import com.example.library.repository.LibrarianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.io.File;

@SpringBootApplication
public class LibraryApplication implements CommandLineRunner {
	@Autowired
	LibrarianRepository uRepo;
	@Autowired
	BookRepository bRepo;


	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		File markerFile = new File("data.txt");
		if (!markerFile.exists()) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

			Librarian firstLibrarian = new Librarian("admin", "admin", "admin@admin.com", passwordEncoder.encode("admin"));
			firstLibrarian.setRole("patron");
			Librarian secondLibrarian = new Librarian("Lekso", "Kashia", "lekso@kashia.com", passwordEncoder.encode("123"));

			Book firstBook = new Book("war and peace", "Lev Tolstoy", "978-3-16-148410-0", "war", firstLibrarian);
			Book secondBook = new Book("Vefxistyaosani", "Shota Rustaveli", "478-3-13-148410-4", "diverse", secondLibrarian);


			uRepo.save(firstLibrarian);
			uRepo.save(secondLibrarian);

			bRepo.save(firstBook);
			bRepo.save(secondBook);


			markerFile.createNewFile();
		}
	}

}
