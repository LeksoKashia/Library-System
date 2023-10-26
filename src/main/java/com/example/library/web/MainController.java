package com.example.library.web;

import com.example.library.model.Borrow;
import com.example.library.model.Librarian;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BorrowRepository;
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
import java.util.Objects;

@Controller
public class MainController {

	@Autowired
	private LibrarianRepository librarianRepository;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private BorrowRepository borrowRepository;


	@Autowired
	private BookService bookService;


	@GetMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping("/login")
	public String login() {
		return "authentication/login";
	}

	@GetMapping("/search")
	public String searchPage() {
		return "search";
	}

	@GetMapping("/borrowed")
	public String borrowedBooks(Model model, Principal principal) {
		Librarian librarian = librarianRepository.findByEmail(principal.getName());
		List<Borrow> borrowedBooks = borrowRepository.findByLibrarian(librarian);

		model.addAttribute("borrowedBooks", borrowedBooks);
		return "borrowedBooks";
	}

	@PostMapping("/search")
	public String search(@RequestParam("searchTerm") String searchTerm, @RequestParam("thing") String thing, @RequestParam(name = "bookOptions", required = false) String option, Model model) {

		if (Objects.equals(thing, "book")) {
			if (Objects.equals(option, "title")) {
				List<Book> collection = bookRepository.findByTitleContaining(searchTerm);
				model.addAttribute("books", collection);
			} else if (Objects.equals(option, "author")) {
				List<Book> collection = bookRepository.findByAuthorContaining(searchTerm);
				model.addAttribute("books", collection);
			}else{
				List<Book> collection = bookRepository.findByISBNContaining(searchTerm);
				model.addAttribute("books", collection);
			}
		} else {
			List<Librarian> collection = librarianRepository.findByFirstNameContaining(searchTerm);
			List<Librarian> patrons = new ArrayList<>();
			for (Librarian l : collection) {
				String role = l.getRole();
				if (role != null) {
					patrons.add(l);
				}
			}
			model.addAttribute("patrons", patrons);
		}

		model.addAttribute("thing", thing);
		return "search";
	}

	@PostMapping("/borrow/{id}")
	public String borrowBook(@PathVariable("id") long id, @RequestParam("returnTime") String returnTime, Principal principal) {
		Book b = bookService.getBookById(id);
		Librarian librarian = librarianRepository.findByEmail(principal.getName());

		int returnT = Integer.parseInt(returnTime);

		Borrow borrowBook = new Borrow(b.getTitle(),b.getAuthor(), b.getISBN(), b.getGenre(), b.getLibrarian().getId(), null, returnT, librarian);
		borrowRepository.save(borrowBook);

		bookService.deleteById(id);
		return "redirect:/borrowed";
	}

	@RequestMapping("/return/{id}")
	public String returnBook(@PathVariable("id") long id) {
		Borrow b = borrowRepository.findById(id).get();
		Librarian librarian = librarianRepository.findById(b.getPreviousUserId()).get();
		Book book = new Book(b.getName(),b.getAuthor(), b.getISBN(), b.getGenre(),librarian);
		bookRepository.save(book);

		borrowRepository.deleteById(id);
		return "redirect:/libraryCatalog";
	}






}
