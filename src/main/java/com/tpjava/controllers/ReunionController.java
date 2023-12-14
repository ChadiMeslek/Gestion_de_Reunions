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
@RequestMapping("/reunions")
public class ReunionController {

    @Autowired
    private ReunionService reunionService;
    @Autowired
    private PersonneService personneService;

    @GetMapping()
    public List<Reunion> getAllReunions() {
        return reunionService.getAllReunions();
    }

    @PostMapping()
    public Reunion saveReunion(@RequestBody Reunion reunion) {
        return reunionService.saveReunion(reunion);
    }

    @PutMapping("/updateReunion/{id}")
    public Reunion updateReunion(@PathVariable Long id, @RequestBody Reunion newReunion) {
        Reunion oldReunion = reunionService.getReunionById(id);
        oldReunion.setTitre(newReunion.getTitre());
        oldReunion.setDateReunion(newReunion.getDateReunion());
        return reunionService.saveReunion(oldReunion);
    }

    @GetMapping("/{id}")
    public Reunion getReunionById(@PathVariable Long id) {
        return reunionService.getReunionById(id);
    }

    @DeleteMapping("/deleteReunion/{id}")
        public void deleteReunionById(@PathVariable Long id) {
            Reunion reunion = reunionService.getReunionById(id);
            List<Personne> personneListCopy = new ArrayList<>(reunion.getPersonneList());
            for(Personne personne : personneListCopy) {
                reunion.removePersonne(personne);
            }
            reunionService.deleteReunionById(id);
        }

    @PutMapping("/{idReunion}/personnes/{idPersonne}")
    public Reunion addPersonneToReunion(@PathVariable Long idReunion , @PathVariable Long idPersonne)
    {
        Reunion reunion = reunionService.getReunionById(idReunion);
        Personne personne = personneService.getPersonneById(idPersonne);

        reunion.addPersonne(personne);
        return reunionService.saveReunion(reunion);
    }
    @DeleteMapping("/{idReunion}/personnes/{idPersonne}")
    public ResponseEntity<?> removePersonneFromReunion(@PathVariable Long idReunion, @PathVariable Long idPersonne) {
        try {
            Reunion reunion = reunionService.getReunionById(idReunion);
            Personne personne = personneService.getPersonneById(idPersonne);

            if (reunion != null && personne != null) {
                reunion.removePersonne(personne);
                reunionService.saveReunion(reunion);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/{idReunion}/personnes")
    public ResponseEntity<List<Personne>> getPersonnesByReunionId(@PathVariable Long idReunion) {
        Reunion reunion = reunionService.getReunionById(idReunion);
        List<Personne> personnes = reunion.getPersonneList();
        return new ResponseEntity<>(personnes, HttpStatus.OK);
    }
}