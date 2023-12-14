package com.tpjava.repositories;

import com.tpjava.entities.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonneRepo extends JpaRepository<Personne,Long> {
}
