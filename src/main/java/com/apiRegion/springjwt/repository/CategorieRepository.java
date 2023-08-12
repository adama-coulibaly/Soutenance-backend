package com.apiRegion.springjwt.repository;

import com.apiRegion.springjwt.models.CategorieProd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CategorieRepository extends JpaRepository<CategorieProd,Long> {
    CategorieProd findByNomcategories(String nomcategories);


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO categorie_prod (nomcategories) VALUES (\"Aliment\"),(\"Poulet\"),(\"Outils\");",nativeQuery = true)
    void ajouterCategorieProd();
}
