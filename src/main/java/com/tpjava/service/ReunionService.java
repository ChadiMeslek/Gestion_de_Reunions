package com.tpjava.service;

import com.tpjava.entities.Reunion;
import com.tpjava.repositories.ReunionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReunionService {

    @Autowired
    private ReunionRepo reunionRepo;

    public List<Reunion> getAllReunions() {
        return reunionRepo.findAll();
    }

    public Reunion getReunionById(Long id) {
        return reunionRepo.findById(id).orElse(null);
    }

    public Reunion saveReunion(Reunion reunion) {
        return reunionRepo.save(reunion);
    }

    public void deleteReunionById(Long id) {
        reunionRepo.deleteById(id);
    }
}

