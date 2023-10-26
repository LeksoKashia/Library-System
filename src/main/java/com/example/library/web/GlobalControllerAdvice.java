package com.example.library.web;

import com.example.library.model.Librarian;
import com.example.library.repository.LibrarianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private LibrarianRepository librarianRepository;


    @ModelAttribute("data")
    public Boolean globalData(Principal principal) {
        if (principal != null) {
            Librarian librarian = librarianRepository.findByEmail(principal.getName());
            String s = librarian.getRole();
            return s == null;
        }
        return false;
    }
}