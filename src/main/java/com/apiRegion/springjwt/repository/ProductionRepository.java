package com.apiRegion.springjwt.repository;

import com.apiRegion.springjwt.models.Ferme;
import com.apiRegion.springjwt.models.Production;
import com.apiRegion.springjwt.models.Status;
import com.apiRegion.springjwt.models.Typeproduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductionRepository extends JpaRepository<Production, Long> {

     public Production findByTypeproduction(Typeproduction typeproduction);
     public Production findByDateentrer(LocalDate dateentrer);
     public Production findByDatesortie(LocalDate dateentrer);

     public List<Production> findByFerme(Ferme ferme);
     Boolean existsByFermeAndTypeproduction(Ferme ferme,Typeproduction typeproduction);

     Optional<Production> findByFermeAndTypeproduction(Ferme ferme, Typeproduction typeproduction);

     Boolean existsByDateentrerAndDatesortie(LocalDate dateentrer, LocalDate datesortie);
     List<Production> findByDateentrerAndDatesortieAndFerme(LocalDate dateentre,LocalDate datesortie,Ferme ferme);
     List<Production> findByStatus(Status status);



}
