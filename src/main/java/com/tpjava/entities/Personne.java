package com.tpjava.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Iterator;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="personnes")
public class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomPersonne;
    private String prenomPersonne;
    private int age;

    @JsonIgnore
    @ManyToMany(mappedBy = "personneList")
    private List<Reunion> reunionList;

    @PreRemove
    private void removeReunionAssociation() {
        for (Reunion reunion : this.reunionList) {
            reunion.getPersonneList().remove(this);
        }
    }

}
