package com.example.library.service;

import com.example.library.model.Patron;
import com.example.library.repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatronService {

    @Autowired
    private PatronRepository pRepo;

    public void save(Patron b) {
        pRepo.save(b);
    }
    public Patron getPatronById(long id) {
        return pRepo.findById(id).get();
    }
    public void deleteById(long id) {
        pRepo.deleteById(id);
    }


}

