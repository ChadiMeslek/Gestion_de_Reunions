package com.tpjava.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="reunions")
public class Reunion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private Date dateReunion;

    @ManyToMany()
    @JoinTable(
            name="Reunion_Personne",
            joinColumns = @JoinColumn(name = "idReunion"),
            inverseJoinColumns = @JoinColumn(name="idPersonne")
    )
    private List<Personne> personneList;

    public void addPersonne(Personne personne) {
        this.personneList.add(personne);
    }

    public void removePersonne(Personne personne){
        this.personneList.remove(personne);
        if(personne.getReunionList().size()== 1)
        personne.getReunionList().clear();
        else
            personne.getReunionList().remove(this);
    }

}
