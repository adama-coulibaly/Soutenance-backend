package com.apiRegion.springjwt.repository;

import com.apiRegion.springjwt.models.CategorieFormation;
import com.apiRegion.springjwt.models.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface CategorieFormationRepository extends JpaRepository<CategorieFormation,Long> {


    CategorieFormation findByTitreCategorie(String titre);

    @Query(value = "SELECT * FROM `categorie_formation` ORDER BY  categorie_formation.id DESC LIMIT 2;",nativeQuery = true)
    List<CategorieFormation> deuxFormations();

}
