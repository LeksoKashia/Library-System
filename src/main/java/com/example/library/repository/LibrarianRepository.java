package com.example.library.repository;

import com.example.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.library.model.Librarian;

import java.util.List;

@Repository
public interface LibrarianRepository extends JpaRepository<Librarian, Long> {
	Librarian findByEmail(String email);
	List<Librarian> findByFirstNameContaining(String searchTerm);

}



