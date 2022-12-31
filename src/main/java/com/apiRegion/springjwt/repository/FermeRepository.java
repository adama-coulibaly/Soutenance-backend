package com.apiRegion.springjwt.repository;

import com.apiRegion.springjwt.models.Ferme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FermeRepository extends JpaRepository<Ferme, Long> {

    public Ferme findByNomferme(String nomferme);
}
