package com.example.library.repository;

import com.example.library.model.Borrow;
import com.example.library.model.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    List<Borrow> findByLibrarian(Librarian librarian);
}
