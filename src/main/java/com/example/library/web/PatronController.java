package com.example.library.web;

import com.example.library.model.Librarian;
import com.example.library.repository.LibrarianRepository;
import com.example.library.model.Patron;
import com.example.library.service.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class PatronController {

    @Autowired
    private PatronService patronService;

    @Autowired
    private LibrarianRepository librarianRepository;


    @GetMapping("/register_patron")
    public String registerPatron() {
        return "crud/registerPatron";
    }

    @PostMapping("/save_patron")
    public String savePatron(@ModelAttribute Patron patron, Principal principal) {
        Librarian librarian = librarianRepository.findByEmail(principal.getName());
        patron.setLibrarian(librarian);
        patronService.save(patron);

        return "redirect:/myCatalog";
    }

    @RequestMapping("/editPatron/{id}")
    public String editPatron(@PathVariable("id") long id, Model model) {
        Patron p = patronService.getPatronById(id);
        model.addAttribute("patron",p);

        return "crud/editPatron";
    }

    @RequestMapping("/deletePatron/{id}")
    public String deletePatron(@PathVariable("id") long id) {
        patronService.deleteById(id);

        return "redirect:/myCatalog";
    }
}
