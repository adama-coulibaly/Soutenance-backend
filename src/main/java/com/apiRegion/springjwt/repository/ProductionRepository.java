package com.apiRegion.springjwt.repository;

import com.apiRegion.springjwt.models.Ferme;
import com.apiRegion.springjwt.models.Production;
import com.apiRegion.springjwt.models.Typeproduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface ProductionRepository extends JpaRepository<Production, Long> {

     public Production findByTypeproduction(Typeproduction typeproduction);
     public Production findByDateentrer(LocalDate dateentrer);
     public Production findByDatesortie(LocalDate dateentrer);

     public List<Production> findByFerme(Ferme ferme);


}
