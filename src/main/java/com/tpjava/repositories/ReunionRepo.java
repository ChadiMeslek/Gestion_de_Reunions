package com.tpjava.repositories;

import com.tpjava.entities.Reunion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReunionRepo extends JpaRepository<Reunion,Long> {
}
