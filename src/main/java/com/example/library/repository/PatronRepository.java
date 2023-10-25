package com.example.library.repository;

import com.example.library.model.Librarian;
import com.example.library.model.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatronRepository extends JpaRepository<Patron, Long> {
    List<Patron> findByLibrarian(Librarian librarian);
    List<Patron> findByNameContaining(String searchTerm);
}
