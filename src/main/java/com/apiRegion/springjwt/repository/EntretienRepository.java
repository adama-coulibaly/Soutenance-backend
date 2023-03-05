package com.apiRegion.springjwt.repository;

import com.apiRegion.springjwt.models.Entretien;
import com.apiRegion.springjwt.models.Production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EntretienRepository extends JpaRepository<Entretien, Long> {

    Boolean existsByDateentretienAndProduction(LocalDate localDate, Production production);
    List<Entretien> findByProduction(Production production);
    List<Entretien> findByDateentretien(LocalDate date);


}
