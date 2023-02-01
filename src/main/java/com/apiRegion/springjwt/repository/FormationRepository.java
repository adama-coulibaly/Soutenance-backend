package com.apiRegion.springjwt.repository;

import com.apiRegion.springjwt.models.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormationRepository extends JpaRepository<Formation,Long> {

    Formation findByTitreformation(String titreformation);

    @Query(value = "SELECT * FROM `formation` ORDER BY  formation.datedeposte DESC LIMIT 2;",nativeQuery = true)
    List<Formation> deuxFormations();
}
