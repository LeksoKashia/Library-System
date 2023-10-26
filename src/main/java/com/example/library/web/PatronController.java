package com.example.library.web;

import com.example.library.model.Librarian;
import com.example.library.repository.LibrarianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class PatronController {

    @Autowired
    private LibrarianRepository librarianRepository;


    @GetMapping("/register_patron")
    public String registerUser() {
        return "crud/registerPatron";
    }

    @PostMapping("/save_patron")
    public String saveUser(@ModelAttribute Librarian librarian, Principal principal) {
        Librarian l = librarianRepository.findByEmail(principal.getName());
        Long i = l.getId();
        String s =String.valueOf(i);


        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Encode the password
        String encodedPassword = passwordEncoder.encode(librarian.getPassword());
        librarian.setPassword(encodedPassword);

        String role = librarian.getRole();
        if (role.length() == 6){
            librarian.setRole(role + s);
        }



        // Save the librarian with the encoded password
        librarianRepository.save(librarian);

        return "redirect:/myCatalog";
    }


    @RequestMapping("/deletePatron/{id}")
    public String deletePatron(@PathVariable("id") long id) {
        librarianRepository.deleteById(id);
        return "redirect:/myCatalog";
    }

    @RequestMapping("/editPatron/{id}")
    public String editPatron(@PathVariable("id") long id, Model model) {
        Librarian patron = librarianRepository.findById(id).get();
        model.addAttribute("patron",patron);

        return "crud/editPatron";
    }

}
