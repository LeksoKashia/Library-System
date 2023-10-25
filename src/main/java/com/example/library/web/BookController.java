package com.example.library.web;

import com.example.library.model.Librarian;
import com.example.library.repository.BookRepository;
import com.example.library.repository.LibrarianRepository;
import com.example.library.model.Book;
import com.example.library.model.Patron;
import com.example.library.repository.PatronRepository;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private LibrarianRepository librarianRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PatronRepository patronRepository;

    @GetMapping("/register_book")
    public String registerBook() {

        return "crud/registerBook";
    }

    @GetMapping("/myCatalog")
    public String myCatalog(Model model, Principal principal) {
        Librarian librarian = librarianRepository.findByEmail(principal.getName());

        List<Book> books = bookRepository.findByLibrarian(librarian);
        List<Patron> patrons = patronRepository.findByLibrarian(librarian);

        model.addAttribute("books", books);
        model.addAttribute("patrons", patrons);

        return "myCatalog";
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute Book book, Principal principal) {
        Librarian librarian = librarianRepository.findByEmail(principal.getName());
        book.setLibrarian(librarian);
        bookService.save(book);

        return "redirect:/myCatalog";
    }


    @RequestMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") long id,Model model) {
        Book b=bookService.getBookById(id);
        model.addAttribute("book",b);

        return "crud/editBook";
    }

    @RequestMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") long id) {
        bookService.deleteById(id);

        return "redirect:/myCatalog";
    }


    @GetMapping("/libraryCatalog")
    public String all(Model model, Principal principal) {
        List<Book> books = bookRepository.findAll();
        List<Patron> patrons = patronRepository.findAll();

        if (principal != null) {
            Librarian librarian = librarianRepository.findByEmail(principal.getName());
            model.addAttribute("librarian_id", librarian.getId());
        }

        model.addAttribute("books", books);
        model.addAttribute("patrons", patrons);

        return "libraryCatalog";
    }



}



