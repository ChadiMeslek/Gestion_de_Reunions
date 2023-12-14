package com.tpjava.service;

import com.tpjava.entities.Personne;
import com.tpjava.repositories.PersonneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonneService {

    @Autowired
    private PersonneRepo personneRepo;

    public List<Personne> getAllPersonnes() {
        return personneRepo.findAll();
    }

    public Personne getPersonneById(Long id) {
        return personneRepo.findById(id).orElse(null);
    }

    public Personne savePersonne(Personne personne) {
        return personneRepo.save(personne);
    }

    public void deletePersonneById(Long id) {
        personneRepo.deleteById(id);
    }
}

