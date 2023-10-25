package com.example.library.web;

import com.example.library.repository.BookRepository;
import com.example.library.repository.LibrarianRepository;
import com.example.library.model.Book;
import com.example.library.model.Patron;
import com.example.library.repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@Controller
public class MainController {

	@Autowired
	private LibrarianRepository librarianRepository;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private PatronRepository patronRepository;

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
		return "search"; // Create a search.html template for this
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
			List<Patron> collection = patronRepository.findByNameContaining(searchTerm);
			model.addAttribute("patrons", collection);
		}

		model.addAttribute("thing", thing);
		return "search";
	}


}
