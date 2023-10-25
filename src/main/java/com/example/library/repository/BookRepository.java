package com.example.library.repository;

import com.example.library.model.Librarian;
import com.example.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByLibrarian(Librarian librarian);
    List<Book> findByTitleContaining(String searchTerm);
    List<Book> findByAuthorContaining(String searchTerm);
    List<Book> findByISBNContaining(String searchTerm);
}


