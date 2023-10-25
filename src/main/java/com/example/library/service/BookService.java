package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bRepo;

    public void save(Book b) {
        bRepo.save(b);
    }
    public Book getBookById(long id) {
        return bRepo.findById(id).get();
    }
    public void deleteById(long id) {
        bRepo.deleteById(id);
    }


}

