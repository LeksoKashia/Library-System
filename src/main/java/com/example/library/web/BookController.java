package com.example.library.web;

import com.example.library.model.Librarian;
import com.example.library.repository.BookRepository;
import com.example.library.repository.LibrarianRepository;
import com.example.library.model.Book;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private LibrarianRepository librarianRepository;

    @Autowired
    private BookRepository bookRepository;


    @GetMapping("/register_book")
    public String registerBook() {

        return "crud/registerBook";
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

        return "redirect:/libraryCatalog";
    }

    @GetMapping("/libraryCatalog")
    public String all(Model model, Principal principal) {

        if (principal != null) {
            Librarian librarian = librarianRepository.findByEmail(principal.getName());
            model.addAttribute("librarian_id", librarian.getId());
        }
        List<Book> books = bookRepository.findAll();
        List<Librarian> librarians = librarianRepository.findAll();

        List<Librarian> patrons = new ArrayList<>();

        for (Librarian l : librarians) {
            String role = l.getRole();
            if (role != null) {
                patrons.add(l);
            }
        }



        model.addAttribute("books", books);
        model.addAttribute("patrons", patrons);


        return "libraryCatalog";
    }

    @GetMapping("/myCatalog")
    public String myCatalog(Model model, Principal principal) {
        Librarian librarian = librarianRepository.findByEmail(principal.getName());
        Long i = librarian.getId();
        String s = String.valueOf(i);

        List<Book> books = bookRepository.findByLibrarian(librarian);
        List<Librarian> librarians = librarianRepository.findAll();

        List<Librarian> patrons = new ArrayList<>();


        for (Librarian l : librarians) {
            String role = l.getRole();
            if (role != null && s.equals(role.substring(6))) {
                patrons.add(l);
            }
        }
        model.addAttribute("books", books);
        model.addAttribute("patrons", patrons);

        return "myCatalog";
    }





}



