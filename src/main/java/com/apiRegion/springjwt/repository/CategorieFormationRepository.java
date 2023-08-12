package com.apiRegion.springjwt.repository;

import com.apiRegion.springjwt.models.CategorieFormation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface CategorieFormationRepository extends JpaRepository<CategorieFormation,Long> {


    CategorieFormation findByTitreCategorie(String titre);

}
