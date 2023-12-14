package com.tpjava.controllers;

import com.tpjava.entities.Personne;
import com.tpjava.entities.Reunion;
import com.tpjava.service.PersonneService;
import com.tpjava.service.ReunionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/personnes")
public class PersonneController {

    @Autowired
    private PersonneService personneService;
    @Autowired
    private ReunionService reunionService;


    @GetMapping()
    public List<Personne> getAllPersonnes()
    {
        return personneService.getAllPersonnes();
    }

    @PostMapping()
    public Personne savePersonne(@RequestBody Personne personne)
    {
        return personneService.savePersonne(personne);
    }

    @PutMapping("/updatePersonne/{id}")
    public Personne updatePersonne(@PathVariable Long id ,@RequestBody Personne newpersonne)
    {
        Personne oldpersonne = personneService.getPersonneById(id);
        oldpersonne.setNomPersonne(newpersonne.getNomPersonne());
        oldpersonne.setPrenomPersonne(newpersonne.getPrenomPersonne());
        oldpersonne.setAge(newpersonne.getAge());
        return personneService.savePersonne(oldpersonne);
    }

    @GetMapping("/{id}")
    public Personne getPersonById(@PathVariable Long id)
    {
        return personneService.getPersonneById(id);
    }

    @DeleteMapping("/deletePersonne/{id}")
    public void deletePersonneById(@PathVariable Long id) {
        Personne personne = personneService.getPersonneById(id);
        List<Reunion> reunionListCopy = new ArrayList<>(personne.getReunionList());

        for (Reunion reunion : reunionListCopy) {
            reunion.removePersonne(personne);
        }

        personneService.deletePersonneById(id);
    }

    @GetMapping("/{id}/reunions")
    public ResponseEntity<List<Reunion>> getReunionsByPersonneId(@PathVariable Long id) {
        Personne personne = personneService.getPersonneById(id);
        List<Reunion> reunions = personne.getReunionList();
        return new ResponseEntity<>(reunions, HttpStatus.OK);
    }

}
